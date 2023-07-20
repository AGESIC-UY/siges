package com.sofis.business.wrapper;

import com.sofis.wekanapiclient.invoker.ApiClient;

public class WekanApiClientWrapper {

	private ApiClient apiClient;
	private String loginId;

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
