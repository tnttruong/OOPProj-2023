package libraryProject;
import java.util.*;

import javax.lang.model.util.ElementScanner14;
public class Member extends Library{
	private String user;
	private String pass;
	private int restriction;
	public Member(String user,String pass)
	{
		super(user,5);
		this.user=user;
		this.pass=pass;
		this.restriction=0;
	}
	public Member(String user,String pass,int restriction)
	{
		super(user,5);
		this.user=user;
		this.pass=pass;
		this.restriction=restriction;
	}
	public Member()
	{
		super(" ",5);
		this.user="";
		this.pass="";
		this.restriction=0;
	}
	public String name()
	{
		return user;
	}
	public boolean password_cmp(String pass)
	{
		return this.pass.equals(pass);
	}
	public int permission()
	{
		return this.restriction;
	}
	public void change_restriction(int restriction)
	{
		this.restriction=restriction;
	}
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the library!");
		boolean bool=true;
		String user;
		String pass;
		int count=0;
		int inp;
		int restriction;
		boolean repeat=false;
		boolean logged=false;
		Member reader= new Member();
		Member member_list[]=new Member[50];
		Library lib=new Library("The library",50);
		lib.add_book(new Book("The Hunger Games","Suzanne Collins",0,"Young adult"));
		lib.add_book(new Book("Harry Potter and the Order of the Phoenix"," J.K. Rowling",1,"Fantasy"));
		lib.add_book(new Book("Pride and Prejudice","Jane Austen",2,"Classics"));
		lib.add_book(new Book("Industrial society and its future","Ted Kazynski",3,"Nonfiction"));
		lib.add_book(new Book("A Game of Thrones","George R. R. Martin",4,"Fantasy"));
		int book_index;
		Book tmp_book;
		while(bool)
		{
			System.out.print("Press 1 to login, 2 to register, 3 to view members, 4 to view all books ,5 to rent a book, 6 to return a book,7 to exit");
			if(logged)
			{
				if(reader.restriction==1)
					System.out.print(" ,8 to add a book to the library, 9 to remove a book from the library");
			}
			System.out.println();
			try
			{
			inp = kb.nextInt();
			}
			catch (InputMismatchException e)
     	 	{ 
         		System.out.println("Invalid input run the program again");
				inp=6;
     	 	}
			switch (inp) {
			case 1:
				try
				{
					System.out.println("Enter username:");
					user = kb.next();
					System.out.println("Enter pass:");
					pass = kb.next();
				}
				catch (InputMismatchException e)
				{ 
					System.out.println("Invalid input run the program again");
					inp=6;
					break;
				}
				for(int i=0;i<count;i++)
				{
					if(member_list[i].name().equals(user))
					{
						if(member_list[i].password_cmp(pass))
						{
							logged=true;
							System.out.println("you have successfully logged in");
						}
						else
						{
							System.out.println("incorrect password");
						}
						break;
					}
				}
				if(!logged)
				{
					System.out.println("your username or password was incorrect");
				}
				break;
			case 2:
				try
				{
					System.out.println("Enter username:");
					user = kb.next();
					System.out.println("Enter pass:");
					pass = kb.next();
					System.out.println("Enter a restriction:");
					restriction = kb.nextInt();
					repeat=false;
					for(int i=0;i<count;i++)
					{
						if(member_list[i].name().equals(user))
						{
							System.out.println("Username already in use");
							repeat=true;
							break;
						}
					}
					if(!repeat)
					{
						reader=new Member(user,pass,restriction);
						member_list[count]=reader;
						count++;
					}
				}
				catch (InputMismatchException e)
				{ 
					System.out.println("Invalid input run the program again");
					inp=6;
				}
				break;
			case 3:
				System.out.println("Users");
				for(int i=0;i<count;i++)
				{
					System.out.println(member_list[i].name());
				}
				break;
			case 4:
				lib.list_books();
				break;
			case 5:
				if(logged)
				{
					try
					{
						System.out.println("pick a book to rent out");
						lib.list_books();
						book_index=kb.nextInt();
						tmp_book=lib.remove_book(book_index);
						reader.add_book(tmp_book);
						System.out.println("You have successfully added a book");
					}
					catch (InputMismatchException e)
					{ 
						System.out.println("Invalid input run the program again");
						inp=6;
						break;
					}
				}
				else
					System.out.println("you have not logged in yet please log in");
				break;
			case 6:
				if(logged)
				{
					System.out.println("Which book do you want to return");
					reader.list_books();
					try
					{
						book_index=kb.nextInt();
					}
					catch (InputMismatchException e)
					{ 
						System.out.println("Invalid input run the program again");
						inp=6;
						break;
					}
					lib.add_book(reader.remove_book(book_index));
					System.out.println("You have successfully returned a book");
				}
				else
					System.out.println("you have not logged in yet please log in");
				break;
			case 7:
				bool=false;
				break;
			case 8:
				if(logged &&(reader.permission()>=1))
				{
					try
					{
					tmp_book=new Book();
					System.out.println("what is the name of the book you want to add");
					tmp_book.name=kb.next();
					System.out.println("what is the author of the book you want to add");
					tmp_book.author=kb.next();
					System.out.println("what is the serial number of the book you want to add");
					tmp_book.serialNumber=kb.nextInt();
					System.out.println("what is the genre of the book you want to add");
					tmp_book.genre=kb.next();
					System.out.println("what is the restriction level of the book you want to add");
					tmp_book.restriction=kb.nextInt();
					lib.add_book(tmp_book);
					}
					catch (InputMismatchException e)
					{ 
						System.out.println("Invalid input run the program again");
						inp=6;
						break;
					}
				}
				else
					System.out.println("Incorrect input please try again");	
				break;
			case 9:
				if(logged && (reader.permission()>=1))
				{
					try
					{
					System.out.println("which book do you want to remove please provide the index that the book is at");
						lib.list_books();
						book_index=kb.nextInt();
						lib.remove_book(book_index);
					}
					catch (InputMismatchException e)
					{ 
						System.out.println("Invalid input run the program again");
						inp=6;
						break;
					}
				}
				else 
					System.out.println("Incorrect input please try again");	
				break;
			default:
				System.out.println("Incorrect input please try again");	
			}
		}
	}
}
