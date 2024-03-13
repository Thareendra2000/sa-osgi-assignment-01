package membershippublisher;


public class Member {
    private String memberId;
    private String name;
    private String address;
    private String teleNo;
    private int noOfBorrowedBooks;

    public Member() {
        super();
    }

    public Member(String memberId, String name, String address, String teleNo, int noOfBorrowedBooks) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.teleNo = teleNo;
        this.noOfBorrowedBooks = noOfBorrowedBooks;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }

    public int getNoOfBorrowedBooks() {
        return noOfBorrowedBooks;
    }

    public void setNoOfBorrowedBooks(int noOfBorrowedBooks) {
        this.noOfBorrowedBooks = noOfBorrowedBooks;
    }
}
