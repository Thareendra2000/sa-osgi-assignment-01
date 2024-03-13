package membershippublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import librarydb.Database;
import librarydb.DatabaseImpl;

public class membershipImpl implements membershipInterface {
    private Connection connection = null;
    private Statement statement = null;
    private Database database;
    private ResultSet resultSet;

    public membershipImpl() {
        database = new DatabaseImpl();
        connection = database.getDatabaseConnection();
    }

    @Override
    public void addMember(Member member) {
        String sqlQueryMember = "INSERT INTO members(memberId, name, address, teleNo, noOfBorrowedBooks) VALUES('" + member.getMemberId() + "', '" + member.getName() + "', '" + member.getAddress() + "', '" + member.getTeleNo() + "', " + member.getNoOfBorrowedBooks() + ")";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQueryMember);
            System.out.println("Member added successfully...");
        } catch (SQLException exc) {
            System.out.println("Error adding member: " + exc.getMessage());
        }
    }

    @Override
    public Member searchMember(String memberId) {
        Member member = null;
        String sqlQueryMember = "SELECT * FROM members WHERE memberId like '" + memberId + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQueryMember);
            if (resultSet.next()) {
                member = new Member(resultSet.getString("memberId"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("teleNo"), resultSet.getInt("noOfBorrowedBooks"));
            }
        } catch (SQLException exc) {
            System.out.println("Error searching member: " + exc.getMessage());
        }

        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sqlQueryMembers = "SELECT * FROM members";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQueryMembers);
            while (resultSet.next()) {
                Member member = new Member(resultSet.getString("memberId"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("teleNo"), resultSet.getInt("noOfBorrowedBooks"));
                members.add(member);
            }
        } catch (SQLException exc) {
            System.out.println("Error getting all members: " + exc.getMessage());
        }

        return members;
    }

    @Override
    public void deleteMember(String memberId) {
        String sqlDeleteMember = "DELETE FROM members WHERE memberId = '" + memberId + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlDeleteMember);
            System.out.println("Member deleted successfully...");
        } catch (SQLException exc) {
            System.out.println("Error deleting member: " + exc.getMessage());
        }
    }
}
