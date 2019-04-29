package per.solax.assist.util;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class YamlReader {

    String fileName;

    private Map<String, Map<String, Object>> properties;

    public YamlReader (String fileName) {
        this.fileName = fileName;
        this.read();
    }

    //init property when class is loaded
    private void read () {
        InputStream in = null;
        try {
            properties = new HashMap<>();
            Yaml yaml = new Yaml();
            in = YamlReader.class.getClassLoader().getResourceAsStream(this.fileName);
            properties = yaml.loadAs(in, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get yaml property
     *
     * @param key
     * @return
     */
    public Object getValueByKey(String root, String key) {
        Map<String, Object> rootProperty = properties.get(root);
        return rootProperty.getOrDefault(key, "");
    }

    public Object getValueByRoot (String root) {
        return properties.get(root);
    }
}
