package implementation_customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao_package.CustomerDao;
import com.model_Package.Customer;


@WebServlet("/")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDao customerDao;
       
    public CustomerServlet() {
        this.customerDao=new CustomerDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	this.doGet(request, response);
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getServletPath();

	        try {
	            switch (action) {
		                case "/new":
		                    showNewForm(request, response);
		                    break;
		                case "/insert":
		                	insertCustomer(request, response);
		                    break;
		                case "/delete":
		                	deleteCustomer(request, response);
		                    break;
		                case "/edit":
		                    showEditForm(request, response);
		                    break;
		                case "/update":
		                	updateCustomer(request, response);
		                    break;
		                default:
		                    listCustomer(request, response);
		                    break;
	                  }
		        } 
	           catch (SQLException ex) {
			            throw new ServletException(ex);
			             }
		     }

	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		        List < Customer > listCustomer = customerDao.selectAllCustomer();
		        request.setAttribute("listCustomer", listCustomer);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		        dispatcher.forward(request, response);
		    }
	
	 private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
			        dispatcher.forward(request, response);
			    }
	 
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException {
			        String email= request.getParameter("email");
			        Customer existingCustomer = customerDao.selectCustomer(email);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
			        request.setAttribute("customer", existingCustomer);
			        dispatcher.forward(request, response);

			    }
	 private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        String firstName = request.getParameter("firstName");
			        String lastName=request.getParameter("lastName");
			        String address=request.getParameter("address");
			        String city=request.getParameter("city");
			        String state=request.getParameter("state");
			        String email = request.getParameter("email");
			        long phone = Long.parseLong(request.getParameter("phone"));
			        
			        Customer newCustomer = new Customer( firstName,  lastName, address, city, state, email, phone);
			        customerDao.insertCustomer(newCustomer);
			        response.sendRedirect("list");
			    }
	 
	 private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        
		    String firstName = request.getParameter("firstName");
	        String lastName=request.getParameter("lastName");
	        String address=request.getParameter("address");
	        String city=request.getParameter("city");
	        String state=request.getParameter("state");
	        long phone = Long.parseLong(request.getParameter("phone"));
	        String email = request.getParameter("email");
	        
			        Customer book = new Customer( firstName,  lastName, address, city, state, email, phone);
			        customerDao.updateCustomer(book);
			        response.sendRedirect("list");
			    }
	 
	 private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        String email=request.getParameter("email");
			        customerDao.deleteCustomer(email);
			        response.sendRedirect("list");

			    }

	
}
