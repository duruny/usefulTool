package xml解析.javaxXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jianpeng.zhang
 * @since 2017/3/6.
 */
@XmlRootElement(name="msgBean")
public class MsgBean
{
    /**
     * 消息号
     */
    private int code;

    private String name;

    private String desc;

    private String msgBody;

    private String template;

    public int getCode()
    {
        return code;
    }

    @XmlAttribute
    public void setCode(int code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    @XmlAttribute
    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    @XmlAttribute
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getMsgBody()
    {
        return msgBody;
    }

    @XmlAttribute
    public void setMsgBody(String msgBody)
    {
        this.msgBody = msgBody;
    }

    public String getTemplate()
    {
        return template;
    }

    @XmlAttribute
    public void setTemplate(String template)
    {
        this.template = template;
    }
}
