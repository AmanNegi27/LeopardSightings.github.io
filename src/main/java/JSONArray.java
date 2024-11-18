
import java.util.ArrayList;
import java.util.List;

public class JSONArray {

    private List<Object> list;

    // Default constructor
    public JSONArray() {
        this.list = new ArrayList<>();
    }

    // Constructor to accept a double array
    public JSONArray(double[] array) {
        this.list = new ArrayList<>();
        for (double value : array) {
            list.add(value); // Autoboxing to Double
        }
    }

    public void put(Object value) {
        list.add(value);
    }

    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (Object obj : list) {
            if (!first) {
                sb.append(", ");
            }
            if (obj instanceof String) {
                sb.append("\"").append(obj).append("\"");
            } else if (obj instanceof JSONObject || obj instanceof JSONArray) {
                sb.append(obj.toString());
            } else {
                sb.append(obj);
            }
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }
}
