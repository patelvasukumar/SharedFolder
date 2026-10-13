package ProjectTaxFiling;

public class TaxCalculation {

// Class
    private double[] monthlySalary;
    private double[] taxOnIncome;
    private double[] incomeAfterTax;
    private Person person;

    TaxCalculation(Person person) {
        this.person = person;
    }

    // getters and setters

    //1.
    public double[] getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double[] monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    //2.
    public double[] getTaxOnIncome() {
        return taxOnIncome;
    }

    public void setTaxOnIncome(double[] taxOnIncome) {
        this.taxOnIncome = taxOnIncome;
    }

    //3.
    public double[] getIncomeAfterTax() {
        return incomeAfterTax;
    }

    public void setIncomeAfterTax(double[] incomeAfterTax) {
        this.incomeAfterTax = incomeAfterTax;
    }

    // methods

    //1.
    public double monthlyIncome() {
        int i = 0;
        int count = person.getPersonCount();

        monthlySalary = new double[count];

        while (i < count) {
            monthlySalary[i] = ((person.getPersonSalaryAr()[i] * 8) * person.getPersonDayAr()[i]) * 4;
            i++;
        }
        return 0;
    }

    //2.
    public double taxOnIncome() {
        int i = 0;
        int count = person.getPersonCount();

        taxOnIncome = new double[count];

        while (i < count) {
            taxOnIncome[i] = (monthlySalary[i] * 20) / 100;
            i++;
        }
        return 0;
    }

    //3.
    public double incomeAfterTax() {
        int i = 0;
        int count = person.getPersonCount();

        incomeAfterTax = new double[count];

        while (i < count) {
            incomeAfterTax[i] = monthlySalary[i] - taxOnIncome[i];
            i++;
        }
        return 0;
    }
}
