package zoo.android.csulb.edu.zoo;

/**
 * Created by Kevin on 3/2/2017.
 */

public class Animal {
    private String name, desc;
    public Animal(String iName, String mDescription) {
        name = iName;

        desc = mDescription;
    }

    public String getName() {
        return name;
    }


    public String getDesc() {
        return desc;
    }
}
