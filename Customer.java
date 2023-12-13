package com.model_Package;

public class Customer {
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String state;
		private String email;
		private long phone;
		
		
		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		public Customer(String firstName, String lastName, String address, String city, String state, String email,
				long phone) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.address = address;
			this.city = city;
			this.state = state;
			this.email = email;
			this.phone = phone;
		}
		@Override
		public String toString() {
			return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
					+ city + ", state=" + state + ", email=" + email + ", phone=" + phone + "]";
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
		}
		public String getStreet() {
			// TODO Auto-generated method stub
			return null;
		}
}
