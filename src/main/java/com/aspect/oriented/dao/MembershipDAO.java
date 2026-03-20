package com.aspect.oriented.dao;

import java.util.List;

public interface MembershipDAO {

    // Core operation
    boolean addMember(String memberName, String membershipType);

    // Existing method (kept for backward compatibility / demo purposes)
    boolean addSillyMember();

    // Fetch operations
    List<String> getAllMembers();

    String getMemberById(int memberId);

    // Update operations
    boolean updateMember(int memberId, String memberName, String membershipType);

    // Delete operations
    boolean deleteMember(int memberId);

    // Utility / lifecycle methods
    void goToSleep();

    void wakeUp();

    boolean isActive();
}
