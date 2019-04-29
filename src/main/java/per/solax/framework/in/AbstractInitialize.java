package per.solax.framework.in;

import per.solax.assist.base.Global;
import per.solax.assist.util.YamlReader;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/10
 * abstract init entry
 *
 */
public abstract class AbstractInitialize {

    final static String applicationYamlName = "application.yaml";

    YamlReader applicationYaml;

    public Map<String, Object> config;

    public AbstractInitialize () {
        this.setConfig();
        this.initApplicationYaml();
    }

    protected abstract void setConfig();

    public abstract Map<String, Object> getConfig ();

    private void initApplicationYaml () {
        // int yaml reader
        YamlReader yamlReader = new YamlReader(applicationYamlName);
        this.applicationYaml = yamlReader;
        // int global config
        Global global = Global.getInstance();
        global.setApplication(applicationYaml);
    }

}