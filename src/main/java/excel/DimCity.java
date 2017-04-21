package excel;

import javax.persistence.Column;
import javax.persistence.Id;


public class DimCity {
	/**
	 *
	 */
	private static final long serialVersionUID = -7924293393232734097L;

	public DimCity(String province, String city) {
		this.province = province;
		this.city = city;
	}

	public DimCity() {}

	@Id
	private Integer id;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 全拼
	 */
	private String pinyin;

	/**
	 * 首字母
	 */
	private String initial;

	/**
	 * 是否热门城市
	 */
	@Column(name = "is_hot")
	private Boolean isHot;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	private Boolean status;

	/**
	 * 获取省份
	 *
	 * @return province - 省份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置省份
	 *
	 * @param province 省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取城市
	 *
	 * @return city - 城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市
	 *
	 * @param city 城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取全拼
	 *
	 * @return pinyin - 全拼
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * 设置全拼
	 *
	 * @param pinyin 全拼
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * 获取首字母
	 *
	 * @return initial - 首字母
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * 设置首字母
	 *
	 * @param initial 首字母
	 */
	public void setInitial(String initial) {
		this.initial = initial;
	}

	/**
	 * 获取是否热门城市
	 *
	 * @return is_hot - 是否热门城市
	 */
	public Boolean getIsHot() {
		return isHot;
	}

	/**
	 * 设置是否热门城市
	 *
	 * @param isHot 是否热门城市
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
}