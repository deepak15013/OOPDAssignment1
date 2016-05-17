package objectmodelpackage;

/**
 * Created by deepaksood619 on 17/5/16.
 */

//Abstract Class used

public abstract class Institute {
    private String instituteName;

    //constructor used for initializing
    Institute(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getInstituteName() {
        return instituteName;
    }
}
