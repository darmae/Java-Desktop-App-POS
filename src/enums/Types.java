package enums;

public class Types {
	private int key;
    private String value;

    public Types(int key, String val) {
        this.key = key;
        this.value = val;
    }
   
    public int getValue() {
        return this.key;
    }

    @Override
    public String toString() {
        return this.value;
    }
    
}
