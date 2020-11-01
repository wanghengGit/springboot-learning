package com.kit.bootmcep.dto;

import com.mcep.commons.request.merge.FileInProperty;
import com.mcep.file.client.FileInfo;
import lombok.Data;

@Data
public class UserAvaterSaveInfo {

	private Long userId;
	@FileInProperty(fileName = "avaterFileUrl", orgName = "orgFileName", updateTime = "fileUpdated")
	private FileInfo avater;
}
