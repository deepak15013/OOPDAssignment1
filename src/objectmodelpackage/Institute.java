package objectmodelpackage;

/**
 * Created by deepaksood619 on 17/5/16.
 */
public abstract class Institute {
    private String instituteName;

    public Institute(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
}
