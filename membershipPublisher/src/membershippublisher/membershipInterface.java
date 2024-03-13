package membershippublisher;

import java.util.List;

public interface membershipInterface {
	 // Method to add a new member
    void addMember(Member member);

    // Method to search for a member by memberId
    Member searchMember(String memberId);

    // Method to retrieve all members
    List<Member> getAllMembers();

    // Method to delete a member by memberId
    void deleteMember(String memberId);
}
