package Model;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotingData {
    /**
     * returns personId given email, pwd
     */
    private static Integer getPersonId(String email, String pwd) {
        Integer personId = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT personId FROM ApplicationUser WHERE email = ? AND pwd = ?");
            ps.setString(1, email);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                personId = rs.getInt("personId");
            }
            conn.close();
            return personId;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * returns voter given personId
     * NOTE: person must be voter
     */
    private static Voter getVoter(Integer personId) {
        String personCode = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String pwd = null;
        String street = null;
        String city = null;
        String zipCode = null;
        String state = null;
        String country = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT p.personCode, p.firstName, p.lastName, au.email, au.pwd, a.street, a.city, a.zipCode, s.state, c.country FROM Person p" +
                    " LEFT JOIN ApplicationUser au ON p.personId = au.personId" +
                    " LEFT JOIN Voter v ON au.applicationUserId = v.applicationUserId" +
                    " LEFT JOIN Address a ON v.addressId = a.addressId" +
                    " LEFT JOIN State s ON a.stateId = s.stateId" +
                    " LEFT JOIN Country c ON s.countryId = c.countryId" +
                    " WHERE p.personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                personCode = rs.getString("personCode");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                pwd = rs.getString("pwd");
                street = rs.getString("street");
                city = rs.getString("city");
                zipCode = rs.getString("zipCode");
                state = rs.getString("state");
                country = rs.getString("country");
            }
            Address address = new Address(street, city, zipCode, state, country);
            Voter voter = new Voter(personCode, firstName, lastName, email, pwd, address);
            conn.close();
            return voter;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * returns auditor given personId
     * NOTE: person must be auditor
     */
    private static Auditor getAuditor(Integer personId) {
        String personCode = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String pwd = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT p.personCode, p.firstName, p.lastName, au.email, au.pwd FROM Person p" +
                    " LEFT JOIN ApplicationUser au ON p.personId = au.personId" +
                    " LEFT JOIN Auditor a ON au.applicationUserId = a.applicationUserId" +
                    " WHERE p.personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                personCode = rs.getString("personCode");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                pwd = rs.getString("pwd");
            }
            Auditor auditor = new Auditor(personCode, firstName, lastName, email, pwd);
            conn.close();
            return auditor;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * checks if voter exists with personId
     *
     * @return
     */
    public static boolean isVoter(Integer personId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Voter WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * returns voter/auditor corresponding to login information if applicationUser exists
     * throws ArgumentException if Login fails
     */
    public static ApplicationUser login(String email, String pwd, String type) throws SQLException {
        Integer personId = getPersonId(email, pwd);
        if (personId == null || (type.equals(Auditor.class.getName()) && isVoter(personId)) || (type.equals(Voter.class.getName()) && !isVoter(personId))) {
            throw new IllegalArgumentException("Invalid Login");
        }
        if (type.equals(Voter.class.getName())) return getVoter(personId);
        else return getAuditor(personId);
    }

    /**
     * retrieves personId from given personCode
     */
    public static Integer getPersonId(String personCode) {
        Integer personId = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT personId FROM Person WHERE personCode = ?");
            ps.setString(1, personCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                personId = rs.getInt("personId");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return personId;
    }

    /**
     * retrieves candidateId from given personId
     */
    public static Integer getCandidateId(Integer personId) {
        Integer candidateId = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT candidateId FROM Candidate WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                candidateId = rs.getInt("candidateId");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return candidateId;
    }

    /**
     * retrieves applicationUserId from given personId
     */
    public static Integer getApplicationUserId(Integer personId) {
        Integer applicationUserId = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT applicationUserId FROM ApplicationUser WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                applicationUserId = rs.getInt("applicationUserId");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return applicationUserId;
    }

    /**
     * retrieves voterId from given personId
     */
    public static Integer getVoterId(Integer personId) {
        Integer voterId = null;


        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT voterId FROM Voter WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                voterId = rs.getInt("voterId");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return voterId;
    }

    /**
     * retrieves auditorId from given personId
     */
    public static Integer getAuditorId(Integer personId) {
        Integer auditorId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT auditorId FROM Auditor WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                auditorId = rs.getInt("auditorId");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return auditorId;
    }

    /**
     * inserts country and return countryId
     */
    private static Integer insertCountry(Address address) {
        Integer countryId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Country (country) values (?)");
            ps.setString(1, address.getCountry());
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                countryId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return countryId;
    }

    /**
     * inserts state and return stateId
     */
    private static Integer insertState(Address address) {
        Integer countryId = insertCountry(address);
        Integer stateId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO State (state, countryId) values (?, ?)");
            ps.setString(1, address.getState());
            ps.setInt(2, countryId);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                stateId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return stateId;
    }

    /**
     * inserts address and returns addressId
     */
    private static Integer insertAddress(Address address) {
        Integer stateId = insertState(address);
        Integer addressId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Address (street, city, zipCode, stateId) values (?, ?, ?, ?)");
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getZipCode());
            ps.setInt(4, stateId);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                addressId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addressId;
    }

    /**
     * Inserts a person into the Person table and returns the personId
     */
    private static Integer insertPerson(String personCode, String firstName, String lastName) {
        Integer personId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Person (personCode, firstName, lastName) values (?, ?, ?)");
            ps.setString(1, personCode);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                personId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return personId;
    }

    /**
     * Inserts a candidate into the Candidate table and returns the candidateId
     */
    public static Integer insertCandidate(Candidate candidate) {
        Integer personId = insertPerson(candidate.getCode(), candidate.getFirstName(), candidate.getLastName());
        Integer candidateId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Candidate (personId) values (?)");
            ps.setInt(1, personId);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                candidateId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return candidateId;
    }

    /**
     * Inserts an ApplicationUser and returns the applicationUserId
     */
    private static int insertApplicationUser(String personCode, String firstName, String lastName, String email, String pwd) {
        Integer personId = insertPerson(personCode, firstName, lastName);
        Integer applicationUserId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO ApplicationUser (personId, email, pwd) values (?, ?, ?)");
            ps.setInt(1, personId);
            ps.setString(2, email);
            ps.setString(3, pwd);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                applicationUserId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return applicationUserId;
    }

    /**
     * Inserts a Voter and returns voterId
     */
    public static Integer insertVoter(Voter voter) {
        Integer applicationUserId = insertApplicationUser(voter.getCode(), voter.getFirstName(), voter.getLastName(), voter.getEmail(), voter.getPwd());
        Integer personId = getPersonId(voter.getCode());
        Integer addressId = insertAddress(voter.getAddress());
        Integer voterId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Voter (personId, applicationUserId, addressId) values (?, ?, ?)");
            ps.setInt(1, personId);
            ps.setInt(2, applicationUserId);
            ps.setInt(3, addressId);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                voterId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return voterId;
    }

    /**
     * Inserts Auditor and returns auditorId
     */
    public static Integer insertAuditor(Auditor auditor) {
        Integer applicationUserId = insertApplicationUser(auditor.getCode(), auditor.getFirstName(), auditor.getLastName(), auditor.getEmail(), auditor.getPwd());
        Integer personId = getPersonId(auditor.getCode());
        Integer auditorId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Auditor (personId, applicationUserId) values (?, ?)");
            ps.setInt(1, personId);
            ps.setInt(2, applicationUserId);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = ps.executeQuery();
            if (rs.next()) {
                auditorId = rs.getInt("LAST_INSERT_ID()");
            }
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return auditorId;
    }

    /**
     * Registers an Auditor
     */
    public static void registerAuditor(Auditor auditor) {
        Integer auditorId = insertAuditor(auditor);
        if (auditorId == null) throw new IllegalArgumentException("Insert auditor unsuccessful");
    }

    /**
     * Registers a Voter
     */
    public static void registerVoter(Voter voter) {
        try {
            Integer voterId = insertVoter(voter);
            if (voterId == null) throw new IllegalArgumentException("Insert voter unsuccessful");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Generates a unique code based on time
     */
    public static String generateUniqueCode() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * gets ballotItemId if it exists
     */
    private static Integer getBallotItemId(BallotItem ballotItem) {
        Integer ballotItemId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT ballotItemId FROM BallotItem WHERE ballotItemCode = ?");
            ps.setString(1, ballotItem.getCode());
            rs = ps.executeQuery();
            if (rs.next()) {
                ballotItemId = rs.getInt("ballotItemId");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ballotItemId;
    }

    /**
     * submit a ballot, returns false if already voted
     *
     * @param ballotAnswers
     * @param voterId
     * @param ballotId
     * @return
     */
    public Boolean sendBallot(List<Triplet> ballotAnswers, Integer voterId, Integer ballotId) {
        if (!insertVoterBallot(voterId, ballotId)) {
            return false;
        } else {
            for (Triplet t : ballotAnswers) {
                String type = (String) t.getValue0();
                if (type.equals(BallotIssue.class.getName())) {
                    updateBallotIssueCount((Integer) t.getValue1(), (boolean) t.getValue2());
                } else if (type.equals(BallotRace.class.getName())) {
                    updateCandidateBallotRace((Integer) t.getValue1(), (Integer) t.getValue2());
                }
            }
            return true;
        }
    }

    /**
     * Returns true if voter has voted on ballot
     */
    public static Boolean voterHasVoted(Integer voterId, Integer ballotId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM VoterBallot WHERE voterId = ? AND ballotId = ?");
            ps.setInt(1, voterId);
            ps.setInt(2, ballotId);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean insertVoterBallot(Integer voterId, Integer ballotId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM VoterBallot WHERE voterId = ? AND ballotId = ?");
            ps.setInt(1, voterId);
            ps.setInt(2, ballotId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false; //already voted on this ballot
            } else {
                ps = conn.prepareStatement("INSERT INTO VoterBallot (voterId, ballotId) values (?,?)");
                ps.setInt(1, voterId);
                ps.setInt(2, ballotId);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCandidateBallotRace(Integer ballotRaceId, Integer candidateId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT voteCount FROM CandidateBallotRace WHERE ballotRaceId = ? AND candidateId = ?");
            ps.setInt(1, ballotRaceId);
            ps.setInt(2, candidateId);
            rs = ps.executeQuery();
            Integer voteCount = null;
            if (rs.next()) {
                voteCount = rs.getInt("voteCount");
            }
            ps = conn.prepareStatement("UPDATE CandidateBallotRace SET voteCount = ? WHERE ballotRaceId = ? AND candidateId = ? AND ballotRaceId = ?");
            ps.setInt(1, voteCount + 1);
            ps.setInt(2, ballotRaceId);
            ps.setInt(3, candidateId);
            ps.setInt(4, ballotRaceId);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateVoterBallot(Integer voterId, Integer ballotId) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM VoterBallot WHERE voterId = ? AND ballotId = ?");
            ps.setInt(1, voterId);
            ps.setInt(2, ballotId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = conn.prepareStatement("INSERT INTO VoterBallot (voterId, ballotId) VALUES (?, ?)");
                ps.setInt(1, voterId);
                ps.setInt(2, ballotId);
                ps.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateBallotIssueCount(Integer ballotIssueId, Boolean choice) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT yesCount, noCount FROM BallotIssue WHERE ballotIssueId = ?");
            ps.setInt(1, ballotIssueId);
            rs = ps.executeQuery();
            Integer yesCount = null;
            Integer noCount = null;
            if (rs.next()) {
                yesCount = rs.getInt("yesCount");
                noCount = rs.getInt("noCount");
            }
            String sqlStatement = (choice) ? "UPDATE BallotIssue SET yesCount = ? WHERE ballotIssueId = ?" : "UPDATE BallotIssue SET noCount = ? WHERE ballotIssueId = ?";
            ps = conn.prepareStatement(sqlStatement);
            if (choice) ps.setInt(1, yesCount + 1);
            else ps.setInt(1, noCount + 1);
            ps.setInt(2, ballotIssueId);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> voteHistory(String firstName, String lastName) {
        List<String> dates = new ArrayList<>();
        Integer voterId = getVoterId(firstName, lastName);
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT b.dateandTime FROM Ballot b" +
                    " LEFT JOIN  VoterBallot v ON v.ballotId = b.ballotId" +
                    " WHERE v.voterId = ?");
            ps.setInt(1, voterId);
            rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("dateandTime"));
            }
            conn.close();
            return dates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getVoterId(String personCode) {
        Integer personId = getPersonId(personCode);
        Integer voterId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT voterId FROM Voter WHERE personId = ?");
            ps.setInt(1, personId);
            rs = ps.executeQuery();
            if (rs.next()) {
                voterId = rs.getInt("voterId");
            }
            conn.close();
            return voterId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> voteHistory(String personCode) {
        List<String> dates = new ArrayList<>();
        Integer voterId = getVoterId(personCode);
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT b.dateandTime FROM Ballot b" +
                    " LEFT JOIN  VoterBallot v ON v.ballotId = b.ballotId" +
                    " WHERE v.voterId = ?");
            ps.setInt(1, voterId);
            rs = ps.executeQuery();
            while (rs.next()) {
                dates.add(rs.getString("dateandTime"));
            }
            conn.close();
            return dates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getVoterId(String firstName, String lastName) {
        Integer voterId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT v.voterId FROM Person p" +
                    " LEFT JOIN Voter v ON v.personId = p.personId" +
                    " WHERE p.firstName = ? AND  p.lastName = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();
            if (rs.next()) {
                voterId = rs.getInt("voterId");
            }
            conn.close();
            return voterId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pair<Integer, Integer> getBallotIssueResult(int ballotIssueId) {
        Integer yesCount = null;
        Integer noCount = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT yesCount, noCount FROM BallotIssue WHERE ballotIssueId = ?");
            ps.setInt(1, ballotIssueId);
            rs = ps.executeQuery();
            if (rs.next()) {
                yesCount = rs.getInt("yesCount");
                noCount = rs.getInt("noCount");
            }
            Pair<Integer, Integer> yesNoPair = new Pair(yesCount, noCount);
            conn.close();
            return yesNoPair;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Candidate getCandidate(Integer candidateId) {
        String personCode = null;
        String firstName = null;
        String lastName = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT p.personCode, p.firstName, p.lastName from Person p " +
                    "LEFT JOIN Candidate c ON c.personId = p.personId WHERE c.candidateId = ?");
            ps.setInt(1, candidateId);
            rs = ps.executeQuery();
            if (rs.next()) {
                personCode = rs.getString("personCode");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
            }
            conn.close();
            Candidate candidate = new Candidate(personCode, firstName, lastName);
            return candidate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Candidate getBallotRaceResult(Integer ballotRaceId) {
        Integer maxVote = null;
        Integer candidateId = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conn = DataSource.getConnection();
            ps = conn.prepareStatement("SELECT MAX(voteCount) as maxVote FROM CandidateBallotRace where ballotRaceId = ?");
            ps.setInt(1, ballotRaceId);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxVote = rs.getInt("maxVote");
            }
            ps = conn.prepareStatement("SELECT candidateId FROM CandidateBallotRace where voteCount = ?");
            ps.setInt(1, maxVote);
            rs = ps.executeQuery();
            if (rs.next()) {
                candidateId = rs.getInt("candidateId");
            }
            conn.close();
            return (candidateId == null) ? null : getCandidate(candidateId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
