package com.powerlong.sharera.vo;

public class ShareVo {

	private Integer share_id;
	private String username;
	private String password;

	public Integer getShare_id() {
		return share_id;
	}

	public void setShare_id(Integer share_id) {
		this.share_id = share_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ShareModel [share_id=" + share_id + ", username=" + username
				+ ", password=" + password + "]";
	}

}
