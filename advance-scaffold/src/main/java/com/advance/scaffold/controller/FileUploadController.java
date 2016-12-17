package com.advance.scaffold.controller;

import com.advance.scaffold.core.controller.ConsoleController;
import com.app.common.Common;
import com.app.common.JsonUtils;
import com.app.common.StringUtils;
import com.app.common.aes.MD5Util;
import com.app.common.constants.TimeConstants;
import com.app.common.date.DateUtils;
import com.app.common.file.FileUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/file")
public class FileUploadController extends ConsoleController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 最大上传文件大小
	private static final int UPLOAD_MAXSIZE = 99 * 1024 * 1024;

	@RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String type) {
		Map<String, String> data = new HashMap<String, String>();
		BufferedOutputStream stream = null;
		if (!file.isEmpty()) {
			try {
				String preUrl = request.getParameter("preUrl");
				// 删除原有文件
				if (StringUtils.isNotBlank(preUrl)) {
					String prePath = FileUtil.getAbsPathOfProject(request) + preUrl;
					FileUtil.delFile(new File(prePath));
				}
				// 获取年月字符串
				String ymStr = DateUtils.getStrDate(new Date(), TimeConstants.F8);
				// 获取文件名
				String fileName = file.getOriginalFilename();
				// 组装文件项目路径
				String uploadPath = "/static/upload/" + type + "/" + ymStr + "/";
				// 获取项目文件绝对路径
				String fileProPath = request.getSession().getServletContext().getRealPath(uploadPath);
				// 新文件名
				String newFileName = getRandom() + "_" + new Date().getTime() + "." + FileUtil.getFilePrefix(fileName);
				// 文件相对路径
				String filePath = uploadPath + newFileName;
				// 文件绝对路径
				String proPath = fileProPath + "/" + newFileName;
				File uploadFile = new File(proPath);
				long upFileSize = file.getSize();
				if (upFileSize > UPLOAD_MAXSIZE) {
					data.put("success", "0");
					data.put("error", "您上传的文件太大了，请选择不超过99MB的文件!");
					return data.toString();
				}
				// 创建文件路径
				if (!uploadFile.getParentFile().exists())
					uploadFile.getParentFile().mkdirs();
				byte[] bytes = file.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				stream.write(bytes);
				String fileMd5 = MD5Util.calcMD5(uploadFile);
				data.put("success", "1");
				data.put("path", filePath);
				data.put("imgPath", FileUtil.reqPath(request) + proPath);
				data.put("fileMd5", fileMd5);
			} catch (Exception e) {
				logger.error(Common.method(), "上传文件出现错误:" + e.getMessage());
			} finally {
				IOUtils.closeQuietly(stream);
			}
		} else {
			data.put("error", "At least one file");
		}
		return JsonUtils.toJson(data);
	}

	/**
	 * 获取随机数
	 *
	 * @return
	 */
	public int getRandom() {
		return new Random().nextInt(90000) + 10000;
	}

}