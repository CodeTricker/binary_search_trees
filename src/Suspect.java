public class Suspect 
{
	private int AFM; //it contains 5 digits
	private String first_name;
	private String last_name;
	private double savings;
	private double taxed_income;
	
	
	public Suspect ( int AFM, String first_name, String last_name, double  savings, double  taxed_income )
	{
		this.AFM = AFM;
		this.first_name = first_name;
		this.last_name = last_name;
		this.savings = savings;
		this.taxed_income = taxed_income;
	}
	
	
	
	public void setAFM(int afm)
	{
		AFM = afm;
	}
	public int key()
	{
		return AFM;
	}
	
	public void setFirst_name(String firstNAme)
	{
		first_name = firstNAme;
	}
	public String getFirst_name()
	{
		return first_name;
	}
	
	public void setLast_name(String lastNAme)
	{
		last_name = lastNAme;
	}
	public String getLast_name()
	{
		return last_name;
	}
	
	public void setSavings(double savings)
	{
		this.savings = savings;
	}
	public double getSavings()
	{
		return savings;
	}
	
	public void setTaxed_income(double taxedIncome)
	{
		taxed_income = taxedIncome;
	}
	public double getTaxed_income()
	{
		return taxed_income;
	}
	
	public double CalculateBlackMoney(double savings, double taxed_income)
	{
		return savings - taxed_income;
	}
	
	public double getBlackMoney()
	{
		return CalculateBlackMoney(savings, taxed_income);
	}
	
	
	
	
	
	public String toString()
	{
		return "\nAFM : " + AFM + ", " + first_name +" " + last_name +", " + "savings: " + savings + ", " + "taxed income: " + taxed_income + ", " + "black money: " + getBlackMoney();
	}
	
	

}
