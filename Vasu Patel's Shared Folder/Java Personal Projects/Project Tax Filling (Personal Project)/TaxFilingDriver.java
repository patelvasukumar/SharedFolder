package ProjectTaxFiling;

public class TaxFilingDriver {

    public static void main(String[] args) {
        
        Person outterObj = new Person();
        TaxCalculation taxObj = new TaxCalculation(outterObj);
        
        // Get number of persons
        outterObj.personCount();
        
        // Initialize arrays based on person count
        outterObj.setPersonNameAr(new String[outterObj.getPersonCount()]);
        outterObj.setPersonDayAr(new int[outterObj.getPersonCount()]);
        outterObj.setPersonSalaryAr(new double[outterObj.getPersonCount()]);
        
        // Collect input data
        System.out.println();
        outterObj.personName();
        System.out.println();
        outterObj.personDay();
        System.out.println();
        outterObj.personSalary();
        System.out.println();

        // Calculate and output results
        taxObj.monthlyIncome();
        taxObj.taxOnIncome();
        taxObj.incomeAfterTax();
        
        System.out.println("------- Tax Filing Summary -------");
        for (int i = 0; i < outterObj.getPersonCount(); i++) {
            System.out.println("Name: " + outterObj.getPersonNameAr()[i]);
            System.out.println("Monthly Income: $" + taxObj.getMonthlySalary()[i]);
            System.out.println("Tax on Income: $" + taxObj.getTaxOnIncome()[i]);
            System.out.println("Income After Tax: $" + taxObj.getIncomeAfterTax()[i]);
            System.out.println("----------------------------------");
        }
    }
}
