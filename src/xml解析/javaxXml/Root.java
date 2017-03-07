package xml解析.javaxXml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jianpeng.zhang
 * @since 2017/3/7.
 */
@XmlRootElement(name="root")
public class Root
{
    private List<ModuleInfo> infos;

    public List<ModuleInfo> getInfos()
    {
        return infos;
    }

    public void setInfos(List<ModuleInfo> infos)
    {
        this.infos = infos;
    }
}
