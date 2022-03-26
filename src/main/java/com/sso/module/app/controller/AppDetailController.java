package com.sso.module.app.controller;

import com.sso.common.ResponseData;
import com.sso.module.app.model.AppDetail;
import com.sso.module.app.model.vo.AppDetailRequestVO;
import com.sso.module.app.service.AppDetailService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 第三方app控制层
 *
 * @Author: golf
 * @Date: 2022/3/26 21:24
 */
@RestController
@RequestMapping("/app")
public class AppDetailController {
    private final AppDetailService appDetailService;

    public AppDetailController(AppDetailService appDetailService) {
        this.appDetailService = appDetailService;
    }

    @PostMapping("/add")
    public ResponseData<String> add(@Validated @RequestBody AppDetail appDetail) {
        appDetailService.addAppDetail(appDetail);
        return ResponseData.success("新增成功");
    }

    @DeleteMapping("/{id}")
    public ResponseData<String> delete(@PathVariable(name = "id") Integer id) {
        appDetailService.deleteAppDetail(id);
        return ResponseData.success("删除成功");
    }

    @PutMapping("/{id}")
    public ResponseData<String> update(@PathVariable("id") Integer id,
                                       @Validated @RequestBody AppDetailRequestVO.UpdateAppVO updateAppVO) {
        appDetailService.updateAppDetail(id, updateAppVO);
        return ResponseData.success("修改成功");
    }

    @GetMapping("/{id}")
    public ResponseData<AppDetail> getAppInfo(@PathVariable(name = "id") Integer id) {
        return ResponseData.success(appDetailService.getAppInfo(id));
    }

    @GetMapping("/list")
    public ResponseData<List<AppDetail>> list() {
        return ResponseData.success(appDetailService.listAppDetail());
    }

}
