package Model;

import java.time.LocalDateTime;
import java.util.List;

public class Ballot {
    private String code;
    private LocalDateTime dateTime;
    private List<BallotItem> ballotItems;

    public Ballot(String code, LocalDateTime dateTime, List<BallotItem> ballotItems) {
        this.code = code;
        this.dateTime = dateTime;
        this.ballotItems = ballotItems;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<BallotItem> getBallotItems() {
        return ballotItems;
    }

    public void setBallotItems(List<BallotItem> ballotItems) {
        this.ballotItems = ballotItems;
    }
}
