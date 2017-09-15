package route;

public class SysConfig {
	public SysConfig() {
	}
	public SysConfig(String sysValue) {
		this.sysValue = sysValue;
	}
    private Integer id;

    private String sysKey;

    private String sysValue;

    /**
     * 是否有效 0-无效 1-有效
     */
    private Boolean isValid;

    private String describtion;

    /**
     * 路径
     */
    private String path;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sys_key
     */
    public String getSysKey() {
        return sysKey;
    }

    /**
     * @param sysKey
     */
    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    /**
     * @return sys_value
     */
    public String getSysValue() {
        return sysValue;
    }

    /**
     * @param sysValue
     */
    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    /**
     * 获取是否有效 0-无效 1-有效
     *
     * @return is_valid - 是否有效 0-无效 1-有效
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * 设置是否有效 0-无效 1-有效
     *
     * @param isValid 是否有效 0-无效 1-有效
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @return describtion
     */
    public String getDescribtion() {
        return describtion;
    }

    /**
     * @param describtion
     */
    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}