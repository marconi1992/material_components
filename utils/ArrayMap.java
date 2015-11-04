package dependencies.material_components.utils;

public class ArrayMap {
    int size = 0;
    private int[] hash = new int[1];
    private String[] data;

    public void put(String key, String value) {
        if (size == 0 && hash.length == 1) {
            hash[size] = key.hashCode();
            data = new String[hash.length * 2];
            data[size] = key;
            data[size + 1] = value;
            size++;
        } else {
            int hashTemp[] = hash;
            hash = new int[hashTemp.length + 1];
            for (int i = 0; i < hash.length; i++) {
                if (i < hashTemp.length) {
                    hash[i] = hashTemp[i];
                } else {
                    hash[i] = key.hashCode();
                }
            }
            String dataTemp[] = data;
            data = new String[hash.length * 2];
            for (int i = 0; i < data.length; i++) {
                if (i >= dataTemp.length) {
                    if (i % 2 == 0) {
                        data[i] = key;
                    } else {
                        data[i] = value;
                    }
                } else {
                    data[i] = dataTemp[i];
                }
            }
            size++;
        }

    }

    public String get(String key) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == key.hashCode()) {
                if (data[i * 2].equals(key)) {
                    return data[(i * 2) + 1];
                }
            }
        }
        return null;
    }


}
