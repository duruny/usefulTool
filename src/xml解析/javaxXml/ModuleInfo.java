package xml解析.javaxXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jianpeng.zhang
 * @since 2017/3/7.
 */
@XmlRootElement(name="moduleinfo")
public class ModuleInfo
{
    private String moduleName;

    private String moduleDesc;

    private List<MsgBean> beanList;

    public ModuleInfo()
    {
    }

    public ModuleInfo(String moduleName)
    {
        this.moduleName = moduleName;
        beanList = new ArrayList<>();
    }

    @XmlAttribute(name="name")
    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public String getModuleDesc()
    {
        return moduleDesc;
    }

    @XmlAttribute(name="desc")
    public void setModuleDesc(String moduleDesc)
    {
        this.moduleDesc = moduleDesc;
    }

    public List<MsgBean> getBeanList()
    {
        return beanList;
    }

    @XmlElement(name="msgBean")
    public void setBeanList(List<MsgBean> beanList)
    {
        this.beanList = beanList;
    }

    public static void main(String[] args)
    {
        ModuleInfo info = new ModuleInfo("namess");
        info.setModuleDesc("dsfjsfnasdf");
        info.setBeanList(new ArrayList<MsgBean>());
        MsgBean bean = new MsgBean();
        bean.setCode(23);
        bean.setDesc("sdfasf");
        bean.setMsgBody("dsfnsa");
        bean.setName("nafooj");
        bean.setTemplate("jasf");

        MsgBean bean2 = new MsgBean();
        bean2.setCode(23);
        bean2.setDesc("sdfasf");
        bean2.setMsgBody("dsfnsa");
        bean2.setName("nafooj");
        bean2.setTemplate("jasf");

        info.getBeanList().add(bean);
        info.getBeanList().add(bean2);

        Root root = new Root();
        root.setInfos(new ArrayList<ModuleInfo>());
        root.getInfos().add(info);

        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<root>\n"
                + "    <infos desc=\"dsfjsfnasdf\" name=\"namess\">\n"
                + "        <msgBean code=\"23\" desc=\"sdfasf\" msgBody=\"dsfnsa\" name=\"nafooj\" template=\"jasf\"/>\n"
                + "        <msgBean code=\"23\" desc=\"sdfasf\" msgBody=\"dsfnsa\" name=\"nafooj\" template=\"jasf\"/>\n"
                + "        <msgBean code=\"23\" desc=\"sdfasf\" msgBody=\"dsfnsa\" name=\"nafooj\" template=\"jasf\"/>\n"
                + "    </infos>\n" + "</root>";
        try
        {
            JAXBContext context = JAXBContext.newInstance(Root.class, ModuleInfo.class, MsgBean.class);    // 获取上下文对象
            Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进

            marshaller.marshal(root, System.out);   // 打印到控制台

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(root, baos);
            String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
            System.out.println(xmlObj);

            JAXBContext context1 = JAXBContext.newInstance(Root.class, ModuleInfo.class, MsgBean.class);    // 获取上下文对象
            Unmarshaller unmarshaller = context1.createUnmarshaller();
            Object o = unmarshaller.unmarshal(new ByteArrayInputStream(s.getBytes()));
            System.out.println(o instanceof Root);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }





    }
}
