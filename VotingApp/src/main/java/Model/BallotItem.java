package Model;

public abstract class BallotItem implements ITyped {
    private String code;
    private String description;
    private String title;

    public BallotItem(String code, String description, String title) {
        this.code = code;
        this.description = description;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract String getType();
}
