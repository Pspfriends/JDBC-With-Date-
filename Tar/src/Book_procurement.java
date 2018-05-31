
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;


public class Book_procurement implements BookLibrarian {
	// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/book";
		String user = "root";
		String password = "";
		Connection conn;
		Statement statement;
		
		Book_procurement() throws SQLException{
			try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			}
			catch(ClassNotFoundException e){
			System.out.println("Unable to load the driver.");
			}
			catch(SQLException e){
			System.out.println("Connection not established.");
			}
			statement = conn.createStatement();
		}
		
		@Override
		public void addBook() {
			try {
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the book details:\n");
				Book b=new Book();
				System.out.println("ISBN:");
				b.setISBN(sc.nextInt());
				System.out.println("Book Title:");
				b.setTitle(sc.next());
				System.out.println("Book Author:");
				b.setAuthor(sc.next());
				System.out.println("Book Publisher:");
				b.setPublisher(sc.next());
				System.out.println("Book Volume:");
				b.setVolume(sc.next());
				System.out.println("Book Purchase Date(YYYY/MM/DD):"); 
			    b.setPurchase_date(sc.next());
				System.out.println("Book Category:");
				b.setBook_type(sc.next());
				String sql = "INSERT INTO book values("+b.getISBN()+",'"+b.getTitle()+"','"+b.getAuthor()+"','"+b.getPublisher()+"','"+b.getVolume()+"',DATE_FORMAT('"+b.getPurchase_date()+"','%Y-%m-%d'),'"+b.getBook_type()+"')";
								
				int row = statement.executeUpdate(sql);
				if (row > 0) {
					System.out.println("Book added to the catalog successfully");
				}
				//conn.close();
			} catch (Exception ex) {
				System.out.println(ex);
				
				ex.printStackTrace();
			} /*catch (Exception ex) {
				ex.printStackTrace();
			}*/

		}

		@Override
		public void updateBook() {
			// TODO Auto-generated method stub
		/*	try {
				// create our java preparedstatement using a sql update query
				String sql = "UPDATE book SET ISBN=?,title=?,author=?,publisher=?,volume=?,purchase_date=?,book_type WHERE ISBN = ?";
				statement = conn.prepareStatement(sql);

				statement.setFloat(1,book.getISBN());
				statement.setString(2,book.getTitle());
				statement.setString(3,book.getAuthor());
				statement.setString(4,book.getPublisher());
				statement.setString(5,book.getVolume());
				java.sql.Date sqlDate = new java.sql.Date(book.getPurchase_date().getTime());
				statement.setDate(6, sqlDate);
				statement.setString(7,book.getBook_type());
				statement.executeUpdate();
				statement.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
		}

		@Override
		public void deleteBook() {
			// TODO Auto-generated method stub
			try {
				Scanner sc=new Scanner(System.in);
				System.out.println("Ente the ISBN NO to Delete :\n");
				Book bd=new Book();
				System.out.println("ISBN:");
				bd.setISBN(sc.nextInt());
				String sql = "Delete from book WHERE ISBN = "+bd.getISBN();
				int row = statement.executeUpdate(sql);
				if (row > 0) {
					System.out.println(bd.getISBN()+" is deleted successfully");
				}
				else
				{
					System.out.println(bd.getISBN()+" Not Found");
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} 

			// TODO Auto-generated method stub

		}

		public void requestBook(){
			try{
			System.out .println("Enter the requested book details:\n");
			Scanner sc=new Scanner(System.in);
			Book b1=new Book();
			System.out.println("Book Title:");
			b1.setTitle(sc.next());
			System.out.println("Book Author:");
			b1.setAuthor(sc.next());
			System.out.println("Book Publisher:");
			b1.setPublisher(sc.next());
			System.out.println("Book Volume:");
			b1.setVolume(sc.next());
			System.out.println("No. of copies:");
			b1.setNo_copies(sc.nextInt());
			System.out.println("Request Date(YYYY/MM/DD):");
			b1.setRequested_date(sc.next());
			
			//b1.setFlag1(2);
			System.out.println("Book Type:");
			b1.setBook_type(sc.next());
			System.out.println("Requested by:");
			b1.setRequested_by(sc.next());
			System.out.println("E-mail Id:");
			b1.setE_mail(sc.next());
			String sql = "INSERT INTO requestbook (title,author,publisher,volume,No_copies, request_date,book_type,requested_by,email_id) values "
			   +"('"+b1.getTitle()+"','"
					+b1.getAuthor()+"', '"
					+b1.getPublisher()+"','"
					+b1.getVolume()+"',"
					+b1.getNo_copies()+","
				    + "DATE_FORMAT('"+b1.getRequested_date()+"','%Y-%m-%d'),'"
					+b1.getBook_type()+"','"
					+b1.getRequested_by()+"','"
					+b1.getE_mail()+"')";
			
			int row = statement.executeUpdate(sql);
			if (row > 0) {
				System.out .println("Request forwarded... Once the book is available, you will be notified !!!!");
				
			}
			//conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			
			ex.printStackTrace();
		} 
		}
		
		public static void main(String args[]) throws SQLException
		{
			Book_procurement book=new Book_procurement(); 
			Scanner sc1=new Scanner(System.in);
			
			while(true){
				System.out.println("Choice:1.Add 2.Request 3.Update 4.Delete\n");
				int ch=sc1.nextInt();
			switch(ch)
			{
			case 1: book.addBook();
			        break;
			case 2: book.requestBook();
			        break;
			case 3: book.updateBook();
					break;
			case 4:	book.deleteBook();
					break;
			default:System.exit(0);
			}
		    }
		}

		
}
