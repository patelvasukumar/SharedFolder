
#include "GUI.h"
#include "StandardLoan.h"
#include "LostItemLoan.h"
#include "Analytics.h"

#include <QTabWidget>
#include <QTableWidget>
#include <QHeaderView>
#include <QLineEdit>
#include <QComboBox>
#include <QDateEdit>
#include <QTextEdit>
#include <QPushButton>
#include <QFormLayout>
#include <QHBoxLayout>
#include <QVBoxLayout>
#include <QMessageBox>
#include <QFileDialog>
#include <QMenuBar>
#include <QDate>
#include <QRegularExpression>
#include <QRegularExpressionValidator>
#include <QAction>
#include <limits>

namespace {
    const QRegularExpression idRegex(R"(^[A-Za-z]{2}\d{4}$)");
    const QRegularExpression nameRegex(R"(^[A-Za-z]+(?: [A-Za-z]+)*$)");
    const QRegularExpression gmailRegex(R"(^[A-Za-z0-9._%+-]+@gmail\.com$)");
    const QRegularExpression phoneRegex(R"(^\d{10}$)");

    bool isValidId(const QString& text) {
        return idRegex.match(text.trimmed()).hasMatch();
    }

    bool isValidName(const QString& text) {
        return nameRegex.match(text.trimmed()).hasMatch();
    }

    bool isValidGmail(const QString& text) {
        return gmailRegex.match(text.trimmed()).hasMatch();
    }

    bool isValidPhone(const QString& text) {
        return phoneRegex.match(text.trimmed()).hasMatch();
    }

    double parseCadAmount(const QString& text, bool* ok = nullptr) {
        QString s = text.trimmed();
        s.remove(QRegularExpression("^CAD\\s*\\$\\s*"));
        s.remove(QRegularExpression("^\\$\\s*"));
        return s.toDouble(ok);
    }

    static QString isoDate(const QDate& d) {
        return d.toString("yyyy-MM-dd");
    }

    bool matchesCaseSensitive(const QString& value, const QString& query) {
        return query.isEmpty() || value.contains(query, Qt::CaseSensitive);
    }
}

GUI::GUI(QWidget* parent)
    : QMainWindow(parent) {
    setWindowTitle("Public Library Management System");
    resize(1300, 850);

    buildUI();

    auto* fileMenu = menuBar()->addMenu("File");
    QAction* saveAction = fileMenu->addAction("Save Data");
    QAction* loadAction = fileMenu->addAction("Load Data");
    connect(saveAction, &QAction::triggered, this, &GUI::saveData);
    connect(loadAction, &QAction::triggered, this, &GUI::loadData);

    try {
        dataManager.loadData(manager, defaultFile.toStdString());
    }
    catch (...) {
    }

    refreshAll();
}

void GUI::warn(const QString& msg) {
    QMessageBox box(this);
    box.setIcon(QMessageBox::Warning);
    box.setWindowTitle("Error");
    box.setTextFormat(Qt::PlainText);
    box.setText(msg);
    box.setStandardButtons(QMessageBox::Ok);
    box.setMinimumWidth(420);
    box.exec();
}

void GUI::buildUI() {
    auto* central = new QWidget(this);
    auto* root = new QVBoxLayout(central);

    tabs = new QTabWidget(central);
    root->addWidget(tabs);

    buildItemsTab();
    buildPatronsTab();
    buildLoansTab();
    buildHoldsTab();
    buildAnalyticsTab();

    setCentralWidget(central);
}

void GUI::buildItemsTab() {
    auto* page = new QWidget;
    auto* layout = new QHBoxLayout(page);

    auto* formBox = new QWidget;
    auto* form = new QFormLayout(formBox);

    itemSearchEdit = new QLineEdit;
    itemSearchFieldEdit = new QComboBox;
    itemSearchFieldEdit->addItems({ "ID", "Title", "Creator", "Format", "Availability" });

    auto* itemSearchRow = new QWidget;
    auto* itemSearchLayout = new QHBoxLayout(itemSearchRow);
    itemSearchLayout->setContentsMargins(0, 0, 0, 0);
    auto* itemSearchBtn = new QPushButton("Search");
    auto* itemClearSearchBtn = new QPushButton("Clear");
    itemSearchLayout->addWidget(itemSearchEdit);
    itemSearchLayout->addWidget(itemSearchFieldEdit);
    itemSearchLayout->addWidget(itemSearchBtn);
    itemSearchLayout->addWidget(itemClearSearchBtn);

    itemIdEdit = new QLineEdit;
    itemTitleEdit = new QLineEdit;
    itemCreatorEdit = new QLineEdit;
    itemFormatEdit = new QComboBox;
    itemFormatEdit->addItems({ "Book", "DVD", "Audiobook", "Magazine" });
    itemAvailEdit = new QComboBox;
    itemAvailEdit->addItems({ "Available", "Not Available" });

    itemIdEdit->setValidator(new QRegularExpressionValidator(idRegex, itemIdEdit));
    itemCreatorEdit->setValidator(new QRegularExpressionValidator(nameRegex, itemCreatorEdit));

    itemIdEdit->setPlaceholderText("IA1000");
    itemTitleEdit->setPlaceholderText("Enter item title");
    itemCreatorEdit->setPlaceholderText("Virat Kohli");

    form->addRow("Search", itemSearchRow);
    form->addRow("Item ID", itemIdEdit);
    form->addRow("Title", itemTitleEdit);
    form->addRow("Creator", itemCreatorEdit);
    form->addRow("Format", itemFormatEdit);
    form->addRow("Availability", itemAvailEdit);

    auto* btnRow = new QHBoxLayout;
    auto* addBtn = new QPushButton("Add");
    auto* updateBtn = new QPushButton("Update");
    auto* deleteBtn = new QPushButton("Delete");
    btnRow->addWidget(addBtn);
    btnRow->addWidget(updateBtn);
    btnRow->addWidget(deleteBtn);
    form->addRow(btnRow);

    itemTable = new QTableWidget;
    itemTable->setColumnCount(5);
    itemTable->setHorizontalHeaderLabels({ "ID", "Title", "Creator", "Format", "Available" });
    itemTable->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    itemTable->setSelectionBehavior(QAbstractItemView::SelectRows);
    itemTable->setEditTriggers(QAbstractItemView::NoEditTriggers);

    layout->addWidget(formBox, 1);
    layout->addWidget(itemTable, 2);

    connect(addBtn, &QPushButton::clicked, this, &GUI::addItem);
    connect(updateBtn, &QPushButton::clicked, this, &GUI::updateItem);
    connect(deleteBtn, &QPushButton::clicked, this, &GUI::deleteItem);

    connect(itemSearchBtn, &QPushButton::clicked, this, [this]() {
        refreshItemTable();
        });
    connect(itemClearSearchBtn, &QPushButton::clicked, this, [this]() {
        itemSearchEdit->clear();
        itemSearchFieldEdit->setCurrentIndex(0);
        refreshItemTable();
        });
    connect(itemSearchEdit, &QLineEdit::returnPressed, this, [this]() {
        refreshItemTable();
        });

    tabs->addTab(page, "Items");
}

void GUI::buildPatronsTab() {
    auto* page = new QWidget;
    auto* layout = new QHBoxLayout(page);

    auto* formBox = new QWidget;
    auto* form = new QFormLayout(formBox);

    patronSearchEdit = new QLineEdit;
    patronSearchFieldEdit = new QComboBox;
    patronSearchFieldEdit->addItems({ "ID", "Name", "Phone", "Email", "Type" });

    auto* patronSearchRow = new QWidget;
    auto* patronSearchLayout = new QHBoxLayout(patronSearchRow);
    patronSearchLayout->setContentsMargins(0, 0, 0, 0);
    auto* patronSearchBtn = new QPushButton("Search");
    auto* patronClearSearchBtn = new QPushButton("Clear");
    patronSearchLayout->addWidget(patronSearchEdit);
    patronSearchLayout->addWidget(patronSearchFieldEdit);
    patronSearchLayout->addWidget(patronSearchBtn);
    patronSearchLayout->addWidget(patronClearSearchBtn);

    patronIdEdit = new QLineEdit;
    patronNameEdit = new QLineEdit;
    patronPhoneEdit = new QLineEdit;
    patronEmailEdit = new QLineEdit;
    patronTypeEdit = new QComboBox;
    patronTypeEdit->addItems({ "Adult", "Child" });

    patronIdEdit->setValidator(new QRegularExpressionValidator(idRegex, patronIdEdit));
    patronNameEdit->setValidator(new QRegularExpressionValidator(nameRegex, patronNameEdit));
    patronPhoneEdit->setValidator(new QRegularExpressionValidator(phoneRegex, patronPhoneEdit));
    patronEmailEdit->setValidator(new QRegularExpressionValidator(gmailRegex, patronEmailEdit));

    patronIdEdit->setPlaceholderText("PA1000");
    patronNameEdit->setPlaceholderText("Jaskirat Singh Rangi");
    patronPhoneEdit->setPlaceholderText("1234567890");
    patronEmailEdit->setPlaceholderText("example@gmail.com");

    form->addRow("Search", patronSearchRow);
    form->addRow("Patron ID", patronIdEdit);
    form->addRow("Name", patronNameEdit);
    form->addRow("Phone", patronPhoneEdit);
    form->addRow("Email", patronEmailEdit);
    form->addRow("Type", patronTypeEdit);

    auto* btnRow = new QHBoxLayout;
    auto* addBtn = new QPushButton("Add");
    auto* updateBtn = new QPushButton("Update");
    auto* clearFineBtn = new QPushButton("Clear Fine");
    auto* deleteBtn = new QPushButton("Delete");
    btnRow->addWidget(addBtn);
    btnRow->addWidget(updateBtn);
    btnRow->addWidget(clearFineBtn);
    btnRow->addWidget(deleteBtn);
    form->addRow(btnRow);

    patronTable = new QTableWidget;
    patronTable->setColumnCount(6);
    patronTable->setHorizontalHeaderLabels({ "ID", "Name", "Phone", "Email", "Type", "Fine (CAD $)" });
    patronTable->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    patronTable->setSelectionBehavior(QAbstractItemView::SelectRows);
    patronTable->setEditTriggers(QAbstractItemView::NoEditTriggers);

    layout->addWidget(formBox, 1);
    layout->addWidget(patronTable, 2);

    connect(addBtn, &QPushButton::clicked, this, &GUI::addPatron);
    connect(updateBtn, &QPushButton::clicked, this, &GUI::updatePatron);
    connect(clearFineBtn, &QPushButton::clicked, this, &GUI::clearFine);
    connect(deleteBtn, &QPushButton::clicked, this, &GUI::deletePatron);

    connect(patronSearchBtn, &QPushButton::clicked, this, [this]() {
        refreshPatronTable();
        });
    connect(patronClearSearchBtn, &QPushButton::clicked, this, [this]() {
        patronSearchEdit->clear();
        patronSearchFieldEdit->setCurrentIndex(0);
        refreshPatronTable();
        });
    connect(patronSearchEdit, &QLineEdit::returnPressed, this, [this]() {
        refreshPatronTable();
        });

    tabs->addTab(page, "Patrons");
}

void GUI::buildLoansTab() {
    auto* page = new QWidget;
    auto* layout = new QHBoxLayout(page);

    auto* formBox = new QWidget;
    auto* form = new QFormLayout(formBox);

    loanSearchEdit = new QLineEdit;
    loanSearchFieldEdit = new QComboBox;
    loanSearchFieldEdit->addItems({ "Loan ID", "Patron ID", "Item ID", "Checkout Date", "Due Date", "Type" });

    auto* loanSearchRow = new QWidget;
    auto* loanSearchLayout = new QHBoxLayout(loanSearchRow);
    loanSearchLayout->setContentsMargins(0, 0, 0, 0);
    auto* loanSearchBtn = new QPushButton("Search");
    auto* loanClearSearchBtn = new QPushButton("Clear");
    loanSearchLayout->addWidget(loanSearchEdit);
    loanSearchLayout->addWidget(loanSearchFieldEdit);
    loanSearchLayout->addWidget(loanSearchBtn);
    loanSearchLayout->addWidget(loanClearSearchBtn);

    loanIdEdit = new QLineEdit;
    loanPatronEdit = new QLineEdit;
    loanItemEdit = new QLineEdit;
    checkoutDateEdit = new QDateEdit(QDate::currentDate());
    checkoutDateEdit->setCalendarPopup(true);
    loanDaysEdit = new QLineEdit("7");
    replacementCostEdit = new QLineEdit("CAD $25.00");
    loanTypeEdit = new QComboBox;
    loanTypeEdit->addItems({ "Standard", "Lost Item" });

    loanIdEdit->setValidator(new QRegularExpressionValidator(idRegex, loanIdEdit));
    loanPatronEdit->setValidator(new QRegularExpressionValidator(idRegex, loanPatronEdit));
    loanItemEdit->setValidator(new QRegularExpressionValidator(idRegex, loanItemEdit));

    loanIdEdit->setPlaceholderText("LA1000");
    loanPatronEdit->setPlaceholderText("PA1000");
    loanItemEdit->setPlaceholderText("IA1000");
    loanDaysEdit->setPlaceholderText("7");
    replacementCostEdit->setPlaceholderText("CAD $25.00");

    form->addRow("Search", loanSearchRow);
    form->addRow("Loan ID", loanIdEdit);
    form->addRow("Patron ID", loanPatronEdit);
    form->addRow("Item ID", loanItemEdit);
    form->addRow("Checkout Date", checkoutDateEdit);
    form->addRow("Loan Days", loanDaysEdit);
    form->addRow("Replacement Cost (CAD $)", replacementCostEdit);
    form->addRow("Loan Type", loanTypeEdit);

    auto* btnRow = new QHBoxLayout;
    auto* issueBtn = new QPushButton("Issue");
    auto* updateBtn = new QPushButton("Update");
    auto* returnBtn = new QPushButton("Return");
    auto* renewBtn = new QPushButton("Renew");
    auto* lostBtn = new QPushButton("Mark Lost");
    btnRow->addWidget(issueBtn);
    btnRow->addWidget(updateBtn);
    btnRow->addWidget(returnBtn);
    btnRow->addWidget(renewBtn);
    btnRow->addWidget(lostBtn);
    form->addRow(btnRow);

    loanTable = new QTableWidget;
    loanTable->setColumnCount(6);
    loanTable->setHorizontalHeaderLabels({ "Loan ID", "Patron ID", "Item ID", "Checkout", "Due", "Type" });
    loanTable->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    loanTable->setSelectionBehavior(QAbstractItemView::SelectRows);
    loanTable->setEditTriggers(QAbstractItemView::NoEditTriggers);

    layout->addWidget(formBox, 1);
    layout->addWidget(loanTable, 2);

    connect(issueBtn, &QPushButton::clicked, this, &GUI::issueLoan);
    connect(updateBtn, &QPushButton::clicked, this, &GUI::updateLoan);
    connect(returnBtn, &QPushButton::clicked, this, &GUI::returnLoan);
    connect(renewBtn, &QPushButton::clicked, this, &GUI::renewLoan);
    connect(lostBtn, &QPushButton::clicked, this, &GUI::processLostItem);

    connect(loanSearchBtn, &QPushButton::clicked, this, [this]() {
        refreshLoanTable();
        });
    connect(loanClearSearchBtn, &QPushButton::clicked, this, [this]() {
        loanSearchEdit->clear();
        loanSearchFieldEdit->setCurrentIndex(0);
        refreshLoanTable();
        });
    connect(loanSearchEdit, &QLineEdit::returnPressed, this, [this]() {
        refreshLoanTable();
        });

    tabs->addTab(page, "Loans");
}

void GUI::buildHoldsTab() {
    auto* page = new QWidget;
    auto* layout = new QHBoxLayout(page);

    auto* formBox = new QWidget;
    auto* form = new QFormLayout(formBox);

    holdSearchEdit = new QLineEdit;
    holdSearchFieldEdit = new QComboBox;
    holdSearchFieldEdit->addItems({ "Hold ID", "Patron ID", "Patron Name", "Item ID", "Pickup Date", "Status" });

    auto* holdSearchRow = new QWidget;
    auto* holdSearchLayout = new QHBoxLayout(holdSearchRow);
    holdSearchLayout->setContentsMargins(0, 0, 0, 0);
    auto* holdSearchBtn = new QPushButton("Search");
    auto* holdClearSearchBtn = new QPushButton("Clear");
    holdSearchLayout->addWidget(holdSearchEdit);
    holdSearchLayout->addWidget(holdSearchFieldEdit);
    holdSearchLayout->addWidget(holdSearchBtn);
    holdSearchLayout->addWidget(holdClearSearchBtn);

    holdIdEdit = new QLineEdit;
    holdPatronEdit = new QLineEdit;
    holdItemEdit = new QLineEdit;
    holdPickupDateEdit = new QDateEdit(QDate::currentDate());
    holdPickupDateEdit->setCalendarPopup(true);
    holdStatusEdit = new QComboBox;
    holdStatusEdit->addItems({ "Item on hold", "Item lost" });

    holdIdEdit->setValidator(new QRegularExpressionValidator(idRegex, holdIdEdit));
    holdPatronEdit->setValidator(new QRegularExpressionValidator(idRegex, holdPatronEdit));
    holdItemEdit->setValidator(new QRegularExpressionValidator(idRegex, holdItemEdit));

    holdIdEdit->setPlaceholderText("HO1000");
    holdPatronEdit->setPlaceholderText("PA1000");
    holdItemEdit->setPlaceholderText("IA1000");

    form->addRow("Search", holdSearchRow);
    form->addRow("Hold ID", holdIdEdit);
    form->addRow("Patron ID", holdPatronEdit);
    form->addRow("Item ID", holdItemEdit);
    form->addRow("Pickup Date", holdPickupDateEdit);
    form->addRow("Status", holdStatusEdit);

    auto* btnRow = new QHBoxLayout;
    auto* addBtn = new QPushButton("Add Hold");
    auto* updateBtn = new QPushButton("Update");
    auto* placeBtn = new QPushButton("Place Item For Loan");
    auto* removeBtn = new QPushButton("Remove Selected");
    btnRow->addWidget(addBtn);
    btnRow->addWidget(updateBtn);
    btnRow->addWidget(placeBtn);
    btnRow->addWidget(removeBtn);
    form->addRow(btnRow);

    holdTable = new QTableWidget;
    holdTable->setColumnCount(6);
    holdTable->setHorizontalHeaderLabels({ "Hold ID", "Patron ID", "Patron Name", "Item ID", "Pickup Date", "Status" });
    holdTable->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    holdTable->setSelectionBehavior(QAbstractItemView::SelectRows);
    holdTable->setEditTriggers(QAbstractItemView::NoEditTriggers);

    layout->addWidget(formBox, 1);
    layout->addWidget(holdTable, 2);

    connect(addBtn, &QPushButton::clicked, this, &GUI::addHold);
    connect(updateBtn, &QPushButton::clicked, this, &GUI::updateHold);
    connect(placeBtn, &QPushButton::clicked, this, &GUI::placeItemForLoan);
    connect(removeBtn, &QPushButton::clicked, this, &GUI::removeHold);

    connect(holdSearchBtn, &QPushButton::clicked, this, [this]() {
        refreshHoldTable();
        });
    connect(holdClearSearchBtn, &QPushButton::clicked, this, [this]() {
        holdSearchEdit->clear();
        holdSearchFieldEdit->setCurrentIndex(0);
        refreshHoldTable();
        });
    connect(holdSearchEdit, &QLineEdit::returnPressed, this, [this]() {
        refreshHoldTable();
        });

    tabs->addTab(page, "Holds");
}

void GUI::buildAnalyticsTab() {
    auto* page = new QWidget;
    auto* layout = new QVBoxLayout(page);

    analyticsBox = new QTextEdit;
    analyticsBox->setReadOnly(true);

    layout->addWidget(analyticsBox);

    tabs->addTab(page, "Analytics");
}

void GUI::refreshItemTable() {
    const QString query = itemSearchEdit ? itemSearchEdit->text().trimmed() : QString();
    const QString field = itemSearchFieldEdit ? itemSearchFieldEdit->currentText() : QString();

    std::vector<LibraryItem*> filtered;
    const auto& items = manager.getItems();

    for (const auto& i : items) {
        bool match = query.isEmpty();

        if (!match) {
            if (field == "ID") {
                match = matchesCaseSensitive(QString::fromStdString(i->getID()), query);
            }
            else if (field == "Title") {
                match = matchesCaseSensitive(QString::fromStdString(i->getTitle()), query);
            }
            else if (field == "Creator") {
                match = matchesCaseSensitive(QString::fromStdString(i->getCreator()), query);
            }
            else if (field == "Format") {
                match = matchesCaseSensitive(QString::fromStdString(i->getFormat()), query);
            }
            else if (field == "Availability") {
                const QString availability = i->getAvailability() ? "Yes" : "No";
                match = matchesCaseSensitive(availability, query);
            }
        }

        if (match) {
            filtered.push_back(i.get());
        }
    }

    itemTable->setRowCount(0);
    itemTable->setRowCount(static_cast<int>(filtered.size()));

    for (int r = 0; r < static_cast<int>(filtered.size()); ++r) {
        const auto* i = filtered[r];
        itemTable->setItem(r, 0, new QTableWidgetItem(QString::fromStdString(i->getID())));
        itemTable->setItem(r, 1, new QTableWidgetItem(QString::fromStdString(i->getTitle())));
        itemTable->setItem(r, 2, new QTableWidgetItem(QString::fromStdString(i->getCreator())));
        itemTable->setItem(r, 3, new QTableWidgetItem(QString::fromStdString(i->getFormat())));
        itemTable->setItem(r, 4, new QTableWidgetItem(i->getAvailability() ? "Yes" : "No"));
    }
}

void GUI::refreshPatronTable() {
    const QString query = patronSearchEdit ? patronSearchEdit->text().trimmed() : QString();
    const QString field = patronSearchFieldEdit ? patronSearchFieldEdit->currentText() : QString();

    std::vector<Patron*> filtered;
    const auto& patrons = manager.getPatrons();

    for (const auto& p : patrons) {
        bool match = query.isEmpty();

        if (!match) {
            if (field == "ID") {
                match = matchesCaseSensitive(QString::fromStdString(p->getID()), query);
            }
            else if (field == "Name") {
                match = matchesCaseSensitive(QString::fromStdString(p->getName()), query);
            }
            else if (field == "Phone") {
                match = matchesCaseSensitive(QString::fromStdString(p->getPhone()), query);
            }
            else if (field == "Email") {
                match = matchesCaseSensitive(QString::fromStdString(p->getEmail()), query);
            }
            else if (field == "Type") {
                match = matchesCaseSensitive(QString::fromStdString(p->getType()), query);
            }
        }

        if (match) {
            filtered.push_back(p.get());
        }
    }

    patronTable->setRowCount(0);
    patronTable->setRowCount(static_cast<int>(filtered.size()));

    for (int r = 0; r < static_cast<int>(filtered.size()); ++r) {
        const auto* p = filtered[r];
        patronTable->setItem(r, 0, new QTableWidgetItem(QString::fromStdString(p->getID())));
        patronTable->setItem(r, 1, new QTableWidgetItem(QString::fromStdString(p->getName())));
        patronTable->setItem(r, 2, new QTableWidgetItem(QString::fromStdString(p->getPhone())));
        patronTable->setItem(r, 3, new QTableWidgetItem(QString::fromStdString(p->getEmail())));
        patronTable->setItem(r, 4, new QTableWidgetItem(QString::fromStdString(p->getType())));

        double fine = manager.getPatronOutstandingFine(p->getID());
        patronTable->setItem(r, 5, new QTableWidgetItem(QString("CAD $%1").arg(fine, 0, 'f', 2)));
    }
}

void GUI::refreshLoanTable() {
    const QString query = loanSearchEdit ? loanSearchEdit->text().trimmed() : QString();
    const QString field = loanSearchFieldEdit ? loanSearchFieldEdit->currentText() : QString();

    std::vector<Loan*> filtered;
    const auto& loans = manager.getLoans();

    for (const auto& l : loans) {
        bool match = query.isEmpty();
        QString type = "Loan";
        if (dynamic_cast<const StandardLoan*>(l.get())) type = "Standard";
        if (dynamic_cast<const LostItemLoan*>(l.get())) type = "Lost";

        if (!match) {
            if (field == "Loan ID") {
                match = matchesCaseSensitive(QString::fromStdString(l->getLoanID()), query);
            }
            else if (field == "Patron ID") {
                match = matchesCaseSensitive(QString::fromStdString(l->getPatronID()), query);
            }
            else if (field == "Item ID") {
                match = matchesCaseSensitive(QString::fromStdString(l->getItemID()), query);
            }
            else if (field == "Checkout Date") {
                match = matchesCaseSensitive(QString::fromStdString(l->getCheckoutDate()), query);
            }
            else if (field == "Due Date") {
                match = matchesCaseSensitive(QString::fromStdString(l->getDueDate()), query);
            }
            else if (field == "Type") {
                match = matchesCaseSensitive(type, query);
            }
        }

        if (match) {
            filtered.push_back(l.get());
        }
    }

    loanTable->setRowCount(0);
    loanTable->setRowCount(static_cast<int>(filtered.size()));

    for (int r = 0; r < static_cast<int>(filtered.size()); ++r) {
        const auto* l = filtered[r];
        QString type = "Loan";
        if (dynamic_cast<const StandardLoan*>(l)) type = "Standard";
        if (dynamic_cast<const LostItemLoan*>(l)) type = "Lost";

        loanTable->setItem(r, 0, new QTableWidgetItem(QString::fromStdString(l->getLoanID())));
        loanTable->setItem(r, 1, new QTableWidgetItem(QString::fromStdString(l->getPatronID())));
        loanTable->setItem(r, 2, new QTableWidgetItem(QString::fromStdString(l->getItemID())));
        loanTable->setItem(r, 3, new QTableWidgetItem(QString::fromStdString(l->getCheckoutDate())));
        loanTable->setItem(r, 4, new QTableWidgetItem(QString::fromStdString(l->getDueDate())));
        loanTable->setItem(r, 5, new QTableWidgetItem(type));
    }
}

void GUI::refreshHoldTable() {
    const QString query = holdSearchEdit ? holdSearchEdit->text().trimmed() : QString();
    const QString field = holdSearchFieldEdit ? holdSearchFieldEdit->currentText() : QString();

    std::vector<HoldRecord> filtered;
    auto holds = manager.getHoldsSnapshot();

    for (const auto& h : holds) {
        bool match = query.isEmpty();

        if (!match) {
            if (field == "Hold ID") {
                match = matchesCaseSensitive(QString::fromStdString(h.holdID), query);
            }
            else if (field == "Patron ID") {
                match = matchesCaseSensitive(QString::fromStdString(h.patronID), query);
            }
            else if (field == "Patron Name") {
                match = matchesCaseSensitive(QString::fromStdString(h.patronName), query);
            }
            else if (field == "Item ID") {
                match = matchesCaseSensitive(QString::fromStdString(h.itemID), query);
            }
            else if (field == "Pickup Date") {
                match = matchesCaseSensitive(QString::fromStdString(h.pickupDate), query);
            }
            else if (field == "Status") {
                match = matchesCaseSensitive(QString::fromStdString(h.status), query);
            }
        }

        if (match) {
            filtered.push_back(h);
        }
    }

    holdTable->setRowCount(0);
    holdTable->setRowCount(static_cast<int>(filtered.size()));

    for (int r = 0; r < static_cast<int>(filtered.size()); ++r) {
        const auto& h = filtered[r];
        holdTable->setItem(r, 0, new QTableWidgetItem(QString::fromStdString(h.holdID)));
        holdTable->setItem(r, 1, new QTableWidgetItem(QString::fromStdString(h.patronID)));
        holdTable->setItem(r, 2, new QTableWidgetItem(QString::fromStdString(h.patronName)));
        holdTable->setItem(r, 3, new QTableWidgetItem(QString::fromStdString(h.itemID)));
        holdTable->setItem(r, 4, new QTableWidgetItem(QString::fromStdString(h.pickupDate)));
        holdTable->setItem(r, 5, new QTableWidgetItem(QString::fromStdString(h.status)));
    }
}

void GUI::refreshAnalytics() {
    QString out;
    out += "PUBLIC LIBRARY ANALYTICS\n";
    out += "------------------------\n";
    out += QString("Total items: %1\n").arg(static_cast<int>(manager.getItems().size()));
    out += QString("Total patrons: %1\n").arg(static_cast<int>(manager.getPatrons().size()));
    out += QString("Active loans: %1\n").arg(static_cast<int>(manager.getLoans().size()));

    AnalyticsEngine<std::unique_ptr<LibraryItem>> itemEngine;
    auto byFormat = itemEngine.computeItemsByFormat(manager.getItems());

    out += "\nItems by format:\n";
    for (const auto& [fmt, count] : byFormat) {
        out += QString::fromStdString(fmt) + ": " + QString::number(count) + "\n";
    }

    AnalyticsEngine<std::unique_ptr<Loan>> loanEngine;
    auto borrowed = loanEngine.computeMostBorrowedItems(manager.getLoans());

    std::string bestId;
    int bestCount = 0;
    for (const auto& [id, count] : borrowed) {
        if (count > bestCount) {
            bestCount = count;
            bestId = id;
        }
    }

    out += "\nMost borrowed item:\n";
    if (!bestId.empty()) {
        if (auto* item = manager.findItemByID(bestId)) {
            out += QString::fromStdString(item->getTitle()) + " (" + QString::fromStdString(bestId) + ")";
        }
        else {
            out += QString::fromStdString(bestId);
        }
        out += " - " + QString::number(bestCount) + " times\n";
    }
    else {
        out += "None\n";
    }

    analyticsBox->setPlainText(out);
}

void GUI::refreshAll() {
    refreshItemTable();
    refreshPatronTable();
    refreshLoanTable();
    refreshHoldTable();
    refreshAnalytics();
}

void GUI::addItem() {
    const QString id = itemIdEdit->text().trimmed();
    const QString title = itemTitleEdit->text().trimmed();
    const QString creator = itemCreatorEdit->text().trimmed();

    if (!isValidId(id)) {
        warn("Item ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (title.isEmpty()) {
        warn("Title cannot be empty.");
        return;
    }

    if (!isValidName(creator)) {
        warn("Creator must contain letters and spaces only.");
        return;
    }

    if (manager.findItemByID(id.toStdString())) {
        warn("Item ID already exists.");
        return;
    }

    manager.addItem(std::make_unique<LibraryItem>(
        id.toStdString(),
        title.toStdString(),
        creator.toStdString(),
        itemFormatEdit->currentText().toStdString(),
        itemAvailEdit->currentText() == "Available"
    ));

    refreshAll();
}

void GUI::updateItem() {
    int row = itemTable->currentRow();
    if (row < 0) {
        warn("Select an item row first.");
        return;
    }

    QString id = itemTable->item(row, 0)->text();
    QString title = itemTitleEdit->text().trimmed();
    QString creator = itemCreatorEdit->text().trimmed();

    if (title.isEmpty()) {
        warn("Title cannot be empty.");
        return;
    }

    if (!isValidName(creator)) {
        warn("Creator must contain letters and spaces only.");
        return;
    }

    if (!manager.editItem(
        id.toStdString(),
        title.toStdString(),
        creator.toStdString(),
        itemFormatEdit->currentText().toStdString(),
        itemAvailEdit->currentText() == "Available")) {
        warn("Could not update item.");
        return;
    }

    refreshAll();
}

void GUI::deleteItem() {
    int row = itemTable->currentRow();
    if (row < 0) {
        warn("Select an item row first.");
        return;
    }

    QString id = itemTable->item(row, 0)->text();
    if (!manager.deleteItem(id.toStdString())) {
        warn("Could not delete item. It may be part of an active loan or hold.");
        return;
    }

    refreshAll();
}

void GUI::addPatron() {
    const QString id = patronIdEdit->text().trimmed();
    const QString name = patronNameEdit->text().trimmed();
    const QString phone = patronPhoneEdit->text().trimmed();
    const QString email = patronEmailEdit->text().trimmed();

    if (!isValidId(id)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidName(name)) {
        warn("Patron name must contain letters and spaces only.");
        return;
    }

    if (!isValidPhone(phone)) {
        warn("Phone number must contain exactly 10 digits.");
        return;
    }

    if (!isValidGmail(email)) {
        warn("Email must end with @gmail.com.");
        return;
    }

    if (manager.findPatronByID(id.toStdString())) {
        warn("Patron ID already exists.");
        return;
    }

    manager.registerPatron(std::make_unique<Patron>(
        id.toStdString(),
        name.toStdString(),
        phone.toStdString(),
        email.toStdString(),
        patronTypeEdit->currentText().toStdString()
    ));

    refreshAll();
}

void GUI::updatePatron() {
    int row = patronTable->currentRow();
    if (row < 0) {
        warn("Select a patron row first.");
        return;
    }

    QString oldId = patronTable->item(row, 0)->text();
    QString newId = patronIdEdit->text().trimmed();
    QString name = patronNameEdit->text().trimmed();
    QString phone = patronPhoneEdit->text().trimmed();
    QString email = patronEmailEdit->text().trimmed();

    if (!isValidId(newId)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidName(name)) {
        warn("Patron name must contain letters and spaces only.");
        return;
    }

    if (!isValidPhone(phone)) {
        warn("Phone number must contain exactly 10 digits.");
        return;
    }

    if (!isValidGmail(email)) {
        warn("Email must end with @gmail.com.");
        return;
    }

    if (newId != oldId && manager.findPatronByID(newId.toStdString())) {
        warn("Patron ID already exists.");
        return;
    }

    Patron* oldPatron = manager.findPatronByID(oldId.toStdString());
    double oldFine = oldPatron ? oldPatron->getFine() : 0.0;

    if (!manager.deletePatron(oldId.toStdString())) {
        warn("Could not update patron.");
        return;
    }

    manager.registerPatron(std::make_unique<Patron>(
        newId.toStdString(),
        name.toStdString(),
        phone.toStdString(),
        email.toStdString(),
        patronTypeEdit->currentText().toStdString()
    ));

    Patron* newPatron = manager.findPatronByID(newId.toStdString());
    if (newPatron) {
        newPatron->addFine(oldFine);
    }

    refreshAll();
}

void GUI::clearFine() {
    int row = patronTable->currentRow();
    if (row < 0) {
        warn("Select a patron row first.");
        return;
    }

    QString id = patronTable->item(row, 0)->text();
    Patron* patron = manager.findPatronByID(id.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    patron->clearFine();
    refreshAll();
}

void GUI::deletePatron() {
    int row = patronTable->currentRow();
    if (row < 0) {
        warn("Select a patron row first.");
        return;
    }

    QString id = patronTable->item(row, 0)->text();
    if (!manager.deletePatron(id.toStdString())) {
        warn("Could not delete patron. Patron may have loans or holds.");
        return;
    }

    refreshAll();
}

void GUI::issueLoan() {
    const QString loanId = loanIdEdit->text().trimmed();
    const QString patronId = loanPatronEdit->text().trimmed();
    const QString itemId = loanItemEdit->text().trimmed();

    if (!isValidId(loanId)) {
        warn("Loan ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(patronId)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(itemId)) {
        warn("Item ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (manager.findLoanByID(loanId.toStdString())) {
        warn("Loan ID already exists.");
        return;
    }

    Patron* patron = manager.findPatronByID(patronId.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    if (manager.getPatronOutstandingFine(patronId.toStdString()) > 0.0) {
        warn("Patron has unpaid fine.");
        return;
    }

    LibraryItem* item = manager.findItemByID(itemId.toStdString());
    if (!item) {
        warn("Item not found.");
        return;
    }

    if (manager.isOnHold(itemId.toStdString())) {
        warn("Item is on hold. Use Place Item For Loan.");
        return;
    }

    if (!item->getAvailability()) {
        warn("Item is not available.");
        return;
    }

    const QString checkout = isoDate(checkoutDateEdit->date());
    bool ok = false;
    int days = loanDaysEdit->text().toInt(&ok);
    if (!ok || days <= 0) days = 7;

    QString due = isoDate(checkoutDateEdit->date().addDays(days));

    if (loanTypeEdit->currentText() == "Lost Item") {
        double cost = parseCadAmount(replacementCostEdit->text(), &ok);
        if (!ok || cost < 0.0) cost = 0.0;

        manager.checkoutItem(std::make_unique<LostItemLoan>(
            loanId.toStdString(),
            patronId.toStdString(),
            itemId.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            cost
        ));
    }
    else {
        manager.checkoutItem(std::make_unique<StandardLoan>(
            loanId.toStdString(),
            patronId.toStdString(),
            itemId.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            days
        ));
    }

    refreshAll();
}

void GUI::updateLoan() {
    int row = loanTable->currentRow();
    if (row < 0) {
        warn("Select a loan row first.");
        return;
    }

    QString oldLoanId = loanTable->item(row, 0)->text();
    QString oldItemId = loanTable->item(row, 2)->text();

    QString newLoanId = loanIdEdit->text().trimmed();
    QString patronId = loanPatronEdit->text().trimmed();
    QString itemId = loanItemEdit->text().trimmed();

    if (!isValidId(newLoanId)) {
        warn("Loan ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(patronId)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(itemId)) {
        warn("Item ID must be 2 letters followed by 4 digits.");
        return;
    }

    Patron* patron = manager.findPatronByID(patronId.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    if (manager.getPatronOutstandingFine(patronId.toStdString()) > 0.0) {
        warn("Patron has unpaid fine.");
        return;
    }

    LibraryItem* item = manager.findItemByID(itemId.toStdString());
    if (!item) {
        warn("Item not found.");
        return;
    }

    if (itemId != oldItemId && !item->getAvailability()) {
        warn("Selected item is not available.");
        return;
    }

    bool ok = false;
    int days = loanDaysEdit->text().toInt(&ok);
    if (!ok || days <= 0) days = 7;

    QString checkout = isoDate(checkoutDateEdit->date());
    QString due = isoDate(checkoutDateEdit->date().addDays(days));

    std::unique_ptr<Loan> newLoan;

    if (loanTypeEdit->currentText() == "Lost Item") {
        double cost = parseCadAmount(replacementCostEdit->text(), &ok);
        if (!ok || cost < 0.0) cost = 0.0;

        newLoan = std::make_unique<LostItemLoan>(
            newLoanId.toStdString(),
            patronId.toStdString(),
            itemId.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            cost
        );
    }
    else {
        newLoan = std::make_unique<StandardLoan>(
            newLoanId.toStdString(),
            patronId.toStdString(),
            itemId.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            days
        );
    }

    if (oldLoanId != newLoanId && manager.findLoanByID(newLoanId.toStdString())) {
        warn("Loan ID already exists.");
        return;
    }

    if (!manager.updateLoan(oldLoanId.toStdString(), std::move(newLoan))) {
        warn("Could not update loan.");
        return;
    }

    refreshAll();
}

void GUI::returnLoan() {
    int row = loanTable->currentRow();
    if (row < 0) {
        warn("Select a loan row first.");
        return;
    }

    QString itemId = loanTable->item(row, 2)->text();
    manager.returnItem(itemId.toStdString());
    refreshAll();
}

void GUI::renewLoan() {
    int row = loanTable->currentRow();
    if (row < 0) {
        warn("Select a loan row first.");
        return;
    }

    QString loanId = loanTable->item(row, 0)->text();
    QString itemId = loanTable->item(row, 2)->text();

    if (manager.isOnHold(itemId.toStdString())) {
        warn("Cannot renew an item with an active hold.");
        return;
    }

    QDate due = QDate::fromString(loanTable->item(row, 4)->text(), "yyyy-MM-dd");
    if (!due.isValid()) {
        due = QDate::currentDate();
    }

    QString newDueDate = isoDate(due.addDays(2));

    if (!manager.renewLoan(loanId.toStdString(), newDueDate.toStdString())) {
        warn("Could not renew loan.");
        return;
    }

    refreshAll();
}

void GUI::processLostItem() {
    int row = loanTable->currentRow();
    if (row < 0) {
        warn("Select a loan row first.");
        return;
    }

    QString loanId = loanTable->item(row, 0)->text();
    bool ok = false;
    double cost = parseCadAmount(replacementCostEdit->text(), &ok);
    if (!ok || cost < 0.0) cost = 0.0;

    if (!manager.processLostItem(loanId.toStdString(), cost)) {
        warn("Could not process lost item.");
        return;
    }

    refreshAll();
}

void GUI::addHold() {
    QString holdID = holdIdEdit->text().trimmed();
    QString patronID = holdPatronEdit->text().trimmed();
    QString itemID = holdItemEdit->text().trimmed();
    QString pickupDate = isoDate(holdPickupDateEdit->date());
    QString status = holdStatusEdit->currentText();

    if (!isValidId(holdID)) {
        warn("Hold ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(patronID)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(itemID)) {
        warn("Item ID must be 2 letters followed by 4 digits.");
        return;
    }

    Patron* patron = manager.findPatronByID(patronID.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    if (manager.getPatronOutstandingFine(patronID.toStdString()) > 0.0) {
        warn("Patron must have 0 fine to place a hold.");
        return;
    }

    LibraryItem* item = manager.findItemByID(itemID.toStdString());
    if (!item) {
        warn("Item not found.");
        return;
    }

    if (manager.checkItemAvailability(itemID.toStdString())) {
        warn("Only loaned items can be placed on hold.");
        return;
    }

    if (!manager.placeHold(
        holdID.toStdString(),
        patronID.toStdString(),
        itemID.toStdString(),
        pickupDate.toStdString(),
        status.toStdString())) {
        warn("Could not add hold. Check for duplicate hold ID, duplicate item hold, or invalid data.");
        return;
    }

    refreshAll();
}

void GUI::updateHold() {
    int row = holdTable->currentRow();
    if (row < 0) {
        warn("Select a hold row first.");
        return;
    }

    QString oldHoldID = holdTable->item(row, 0)->text();

    QString holdID = holdIdEdit->text().trimmed();
    QString patronID = holdPatronEdit->text().trimmed();
    QString itemID = holdItemEdit->text().trimmed();
    QString pickupDate = isoDate(holdPickupDateEdit->date());
    QString status = holdStatusEdit->currentText();

    if (!isValidId(holdID)) {
        warn("Hold ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(patronID)) {
        warn("Patron ID must be 2 letters followed by 4 digits.");
        return;
    }

    if (!isValidId(itemID)) {
        warn("Item ID must be 2 letters followed by 4 digits.");
        return;
    }

    Patron* patron = manager.findPatronByID(patronID.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    if (manager.getPatronOutstandingFine(patronID.toStdString()) > 0.0) {
        warn("Patron must have 0 fine to update a hold.");
        return;
    }

    if (!manager.updateHold(
        oldHoldID.toStdString(),
        patronID.toStdString(),
        itemID.toStdString(),
        pickupDate.toStdString(),
        status.toStdString())) {
        warn("Could not update hold.");
        return;
    }

    if (holdID != oldHoldID) {
        HoldRecord* h = manager.findHoldByID(oldHoldID.toStdString());
        if (h) {
            h->holdID = holdID.toStdString();
        }
    }

    refreshAll();
}

void GUI::placeItemForLoan() {
    int row = holdTable->currentRow();
    if (row < 0) {
        warn("Select a hold row first.");
        return;
    }

    QString holdID = holdTable->item(row, 0)->text();
    QString patronID = holdTable->item(row, 1)->text();
    QString itemID = holdTable->item(row, 3)->text();

    if (holdTable->item(row, 5)->text() == "Item lost") {
        warn("Lost items cannot be moved to loan from hold. Remove the hold manually.");
        return;
    }

    QString loanID = loanIdEdit->text().trimmed();
    if (!isValidId(loanID)) {
        warn("Enter a valid Loan ID first.");
        return;
    }

    if (manager.findLoanByID(loanID.toStdString())) {
        warn("Loan ID already exists.");
        return;
    }

    Patron* patron = manager.findPatronByID(patronID.toStdString());
    if (!patron) {
        warn("Patron not found.");
        return;
    }

    if (manager.getPatronOutstandingFine(patronID.toStdString()) > 0.0) {
        warn("Patron has unpaid fine.");
        return;
    }

    bool ok = false;
    int days = loanDaysEdit->text().toInt(&ok);
    if (!ok || days <= 0) days = 7;

    QString checkout = isoDate(checkoutDateEdit->date());
    QString due = isoDate(checkoutDateEdit->date().addDays(days));

    std::unique_ptr<Loan> newLoan;

    if (loanTypeEdit->currentText() == "Lost Item") {
        double cost = parseCadAmount(replacementCostEdit->text(), &ok);
        if (!ok || cost < 0.0) cost = 0.0;

        newLoan = std::make_unique<LostItemLoan>(
            loanID.toStdString(),
            patronID.toStdString(),
            itemID.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            cost
        );
    }
    else {
        newLoan = std::make_unique<StandardLoan>(
            loanID.toStdString(),
            patronID.toStdString(),
            itemID.toStdString(),
            checkout.toStdString(),
            due.toStdString(),
            days
        );
    }

    if (!manager.placeItemForLoan(holdID.toStdString(), std::move(newLoan))) {
        warn("Could not place item for loan.");
        return;
    }

    refreshAll();
}

void GUI::removeHold() {
    int row = holdTable->currentRow();
    if (row < 0) {
        warn("Select a hold row first.");
        return;
    }

    QString id = holdTable->item(row, 0)->text();
    if (!manager.removeHold(id.toStdString())) {
        warn("Could not remove hold.");
        return;
    }

    refreshAll();
}

void GUI::saveData() {
    QString path = QFileDialog::getSaveFileName(this, "Save Library Data", defaultFile, "JSON Files (*.json)");
    if (path.isEmpty()) return;
    defaultFile = path;

    try {
        dataManager.saveData(manager, path.toStdString());
        QMessageBox::information(this, "Saved", "Data saved successfully.");
    }
    catch (const std::exception& e) {
        QMessageBox::critical(this, "Save Error", e.what());
    }
}

void GUI::loadData() {
    QString path = QFileDialog::getOpenFileName(this, "Load Library Data", defaultFile, "JSON Files (*.json)");
    if (path.isEmpty()) return;
    defaultFile = path;

    try {
        dataManager.loadData(manager, path.toStdString());
        refreshAll();
        QMessageBox::information(this, "Loaded", "Data loaded successfully.");
    }
    catch (const std::exception& e) {
        QMessageBox::critical(this, "Load Error", e.what());
    }
}