#pragma once

#include <QMainWindow>
#include <QString>
#include <memory>

class QTabWidget;
class QTableWidget;
class QLineEdit;
class QComboBox;
class QDateEdit;
class QTextEdit;
class QWidget;

#include "LibraryManager.h"
#include "DataManager.h"

class GUI : public QMainWindow {
    Q_OBJECT

public:
    explicit GUI(QWidget* parent = nullptr);

private:
    void buildUI();
    void buildItemsTab();
    void buildPatronsTab();
    void buildLoansTab();
    void buildHoldsTab();
    void buildAnalyticsTab();

    void refreshItemTable();
    void refreshPatronTable();
    void refreshLoanTable();
    void refreshHoldTable();
    void refreshAnalytics();
    void refreshAll();

    void warn(const QString& msg);

    void addItem();
    void updateItem();
    void deleteItem();

    void addPatron();
    void updatePatron();
    void clearFine();
    void deletePatron();

    void issueLoan();
    void updateLoan();
    void returnLoan();
    void renewLoan();
    void processLostItem();

    void addHold();
    void updateHold();
    void placeItemForLoan();
    void removeHold();

    void saveData();
    void loadData();

private:
    QTabWidget* tabs = nullptr;

    QLineEdit* itemIdEdit = nullptr;
    QLineEdit* itemTitleEdit = nullptr;
    QLineEdit* itemCreatorEdit = nullptr;
    QComboBox* itemFormatEdit = nullptr;
    QComboBox* itemAvailEdit = nullptr;
    QLineEdit* itemSearchEdit = nullptr;
    QComboBox* itemSearchFieldEdit = nullptr;
    QTableWidget* itemTable = nullptr;

    QLineEdit* patronIdEdit = nullptr;
    QLineEdit* patronNameEdit = nullptr;
    QLineEdit* patronPhoneEdit = nullptr;
    QLineEdit* patronEmailEdit = nullptr;
    QComboBox* patronTypeEdit = nullptr;
    QLineEdit* patronSearchEdit = nullptr;
    QComboBox* patronSearchFieldEdit = nullptr;
    QTableWidget* patronTable = nullptr;

    QLineEdit* loanIdEdit = nullptr;
    QLineEdit* loanPatronEdit = nullptr;
    QLineEdit* loanItemEdit = nullptr;
    QDateEdit* checkoutDateEdit = nullptr;
    QLineEdit* loanDaysEdit = nullptr;
    QLineEdit* replacementCostEdit = nullptr;
    QComboBox* loanTypeEdit = nullptr;
    QLineEdit* loanSearchEdit = nullptr;
    QComboBox* loanSearchFieldEdit = nullptr;
    QTableWidget* loanTable = nullptr;

    QLineEdit* holdIdEdit = nullptr;
    QLineEdit* holdPatronEdit = nullptr;
    QLineEdit* holdItemEdit = nullptr;
    QDateEdit* holdPickupDateEdit = nullptr;
    QComboBox* holdStatusEdit = nullptr;
    QLineEdit* holdSearchEdit = nullptr;
    QComboBox* holdSearchFieldEdit = nullptr;
    QTableWidget* holdTable = nullptr;

    QTextEdit* analyticsBox = nullptr;

    LibraryManager manager;
    DataManager dataManager;
    QString defaultFile = "data/library.json";
};