 import java.util.Scanner;
 //in method loadFile the name of the file is to be written without "" 

public class Menu 
{
	
	public static void main (String[] args)
	{
		
		
		ST SuspectsST = new ST();
		
		
		
		Scanner scanner = new Scanner(System.in);
		//////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////
		
		//we initialize our variables
		boolean exitMenuDisplay_flag = false;
		int select = 6;
		int select1 = 6;
		int select2 = 6;
		int select3 = 6;
		
		//////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////
		
		while(exitMenuDisplay_flag == false)
		{
			//we display our menu
			centralMenuDisplay();
			System.out.println("Please select your action by typing the number of the choice: \n");
			scanner.reset();
			select = scanner.nextInt();
			//we ensure that the user will select a valid choice
			while(select<0 || select >5)
			{
				System.out.println("Please select a valid choice: \n");
				select = scanner.nextInt();
			}
			
			
			System.out.println("You selected the choice number: " + select + "\n");
			
			//we display the rest of our menus according to the user's choice
			switch (select)
			{
				case 1: insertDeleteMenuDisplay();
						System.out.println("Please select your action by typing the number of the choice: \n");
						select1 = scanner.nextInt();
						while(select1<0 || select1 >3)
						{
							System.out.println("Please select a valid choice: \n");
							select1 = scanner.nextInt();
						}
						System.out.println("You selected the choice number: " + select1 + "\n");
						switch (select1)
						{
							case 1: simpleInsertion(SuspectsST, scanner);
									break;
							case 2: rootInsertion(SuspectsST, scanner);
									break;
							case 3: removeSuspect(SuspectsST, scanner);
									break;
							case 0: break;
						}
						//end of this switch
						break;
				case 2: searchMenuDisplay();
						System.out.println("Please select your action by typing the number of the choice: \n");
						select2 = scanner.nextInt();
						while(select2<0 || select2 >2)
						{
							System.out.println("Please select a valid choice: \n");
							select2 = scanner.nextInt();
						}
						System.out.println("You selected the choice number: " + select2 + "\n");
						switch (select2)
						{
							case 1: searchAFM(SuspectsST, scanner);
									break;
							case 2: searchLastName(SuspectsST, scanner);
									break;
							case 0: break;
						
						}
						//end of this switch
						break;
				case 3: printMenuDisplay();
						System.out.println("Please select your action by typing the number of the choice: \n");
						select3 = scanner.nextInt();
						while(select3<0 || select3 >2)
						{
							System.out.println("Please select a valid choice: \n");
							select3 = scanner.nextInt();
						}
						System.out.println("You selected the choice number: " + select3 + "\n");
						switch (select3)
						{
							case 1: printALL(SuspectsST);
									break;
							case 2: printTopk(SuspectsST, scanner);
									break;
							case 0: break;
						}
						//end of this switch
						break;
				case 4:	//SuspectsST.load(path_of_file);
						loadFile(SuspectsST, scanner);
						break;
				case 5: MeanOfSavings(SuspectsST);
						break;
				//we turn the exitMenuDisplay_flag into true
				//so that we stop our while and exit(finish) our program		
				case 0: exitMenuDisplay();
						exitMenuDisplay_flag = true;
						break;
				default: break;
			
			
			
			}
		//end of first switch
	}
	//end of while
		
	}
	
	
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
	
	//these are the menus to show
	
	
	private static void centralMenuDisplay()
	{
		System.out.println("-------------------");
		System.out.println("Welcome to the MENU");
		System.out.println("-------------------");
		System.out.println("1: INSERTION/DELETION of suspects");
		System.out.println("2: SEARCH suspects");
		System.out.println("3: PRINT suspects");
		System.out.println("4: LOAD a file of suspects");
		System.out.println("5: CALCULATE MEAN of savings of suspects");
		System.out.println("0: EXIT the program");
		
		
	}
	
	private static void insertDeleteMenuDisplay()
	{
		System.out.println("1: SIMPLE INSERTION of a suspect");
		System.out.println("2: ROOT INSERTION of a suspect");
		System.out.println("3: DELETION of a suspect");
		System.out.println("0: Return to the MENU");
	}
	
	
	private static void searchMenuDisplay()
	{
		System.out.println("1: SEARCH a suspect according his AFM");
		System.out.println("2: SEARCH a suspect according his LASTNAME");
		System.out.println("0: Return to the MENU");
	}
	

	private static void printMenuDisplay()
	{
		System.out.println("1: PRINT all the suspects");
		System.out.println("2: PRINT the top k suspects");
		System.out.println("0: Return to the MENU");
	}
	
	private static void exitMenuDisplay()
	{
		System.out.println("EXITING THE PROGRAM...");
		System.out.println("...................");
		System.out.println("...................");
	}
	
	
	//these are the functions we can do according the user's choice
	
	
	///////////////////////////////////////////////
	//////////////////////////////////////////////
	/////////////START OF INSERTION///////////////
	private static Suspect insertion(Scanner scanner)
	{
		
		int AFM;
		String firstName;
		String lastName;
		Double savings;
		Double taxedIncome;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the AFM of the suspect: ");
		AFM = scanner.nextInt();
		//in case the user wants to return to the Menu
		if(AFM == 0)
			return null;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the firstname of the suspect: ");
		firstName = scanner.next();
		//in case the user wants to return to the Menu
		if(firstName == "0")
			return null;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the lastname of the suspect: ");
		lastName = scanner.next();
		//in case the user wants to return to the Menu
		if(lastName == "0")
			return null;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the savings of the suspect: ");
		savings = scanner.nextDouble();
		//in case the user wants to return to the Menu
		if(AFM == 0)
			return null;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the taxed income of the suspect: ");
		taxedIncome = scanner.nextDouble();
		//in case the user wants to return to the Menu
		if(AFM == 0)
			return null;
		
		Suspect suspect = new Suspect(AFM, firstName, lastName, savings, taxedIncome);
		
		return suspect;
	}
	
	
	private static void simpleInsertion(ST SuspectsST, Scanner scanner)
	{
		Suspect suspect = insertion(scanner);
		if(suspect == null)
			return;
		SuspectsST.insert(suspect);
	}
	private static void rootInsertion(ST SuspectsST, Scanner scanner)
	{
		Suspect suspect = insertion(scanner);
		if(suspect == null)
			return;
		SuspectsST.insert_at_root(suspect);
	}
	/////////////END OF INSERTION//////////////////
	///////////////////////////////////////////////
	//////////////////////////////////////////////
	
	private static void removeSuspect(ST SuspectsST, Scanner scanner)
	{
		
		int AFM;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the AFM of the suspect: ");
		AFM = scanner.nextInt();
		//in case the user wants to return to the Menu
		if(AFM==0)
			return;
		
		SuspectsST.remove(AFM);
	}
	
	private static void searchAFM(ST SuspectsST, Scanner scanner)
	{
		
		int AFM;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the AFM of the suspect: ");
		AFM = scanner.nextInt();
		//in case the user wants to return to the Menu
		if(AFM==0)
			return;
		
		System.out.println("The suspect you are looking for: " + SuspectsST.searchByAFM(AFM));
	}
	private static void searchLastName(ST SuspectsST, Scanner scanner)
	{
		
		String lastName;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the lastname of the suspect: ");
		lastName = scanner.next();
		//in case the user wants to return to the Menu
		if(lastName == "0")
			return;
		List<Suspect> suspectList;
		suspectList = SuspectsST.searchByLastname(lastName);
		suspectList.print();
	}
	
	private static void printALL(ST SuspectsST)
	{
		SuspectsST.printTreeByAfm(System.out);
	}
	
	private static void printTopk(ST SuspectsST, Scanner scanner)
	{
		int k;
		
		System.out.println("0: Return to the MENU");
		System.out.println("\nGive the number of the top suspects: ");
		
		k = scanner.nextInt();
		while(k<0 || k>SuspectsST.countOfST())
		{
			System.out.println("Please select a valid choice: \n");
			k = scanner.nextInt();
		}
		//in case the user wants to return to the Menu
		if(k==0)
			return;
		
		SuspectsST.printTopSuspects(k);
		//ST TopSuspectsST = SuspectsST.loadTopSuspects(k);
		//TopSuspectsST.printkMax(k);
	}
	

	
	private static void loadFile(ST SuspectsST, Scanner scanner)
	{
		//C:\\Users\\stelios\\Desktop\\domes3 pros paradosh\\ergasia3\\src\\treeFile.txt;
		//we create a new scanner to use delimiter
		Scanner loadScanner = new Scanner(System.in);
		loadScanner.useDelimiter(";");
		String path_of_file;
		
		System.out.println("\nGive the path of the file, write ; at the end of the name to continue: \n");
		System.out.println("\ni.e:path_of_file.txt; \n");
		
		
		path_of_file = loadScanner.next().trim();
			
		SuspectsST.load(path_of_file);
	}
	
	private static void MeanOfSavings(ST SuspectsST)
	{
		
		System.out.println("\nthe mean of savings is: " + SuspectsST.getMeanSavings());
	}
	
	
	
	
	
	
	
}
