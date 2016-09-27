package management.borrow.book.model;

public class Book {
	private int id;
	private String name;
	private String author;
	private String category;
	private String date;
	private String publisher;
	private String language;
	private String pages;
	private String img;
	private String info;
	private String status;
	private String pay_date;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Book(int id, String name, String author, String category, String date, String publisher, String language,
			String pages, String img, String info, String status, String pay_date) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.category = category;
		this.date = date;
		this.publisher = publisher;
		this.language = language;
		this.pages = pages;
		this.img = img;
		this.info = info;
		this.status = status;
		this.pay_date = pay_date;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
