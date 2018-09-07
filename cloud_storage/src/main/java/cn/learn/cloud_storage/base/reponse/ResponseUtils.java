package cn.learn.cloud_storage.base.reponse;

import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.Map;

public abstract class ResponseUtils {

    /**
     * 返回成功响应.
     */
    public static Response success() {
        return success(null);
    }

    /**
     * 返回成功响应.
     *
     * @param data 数据
     * @return 响应对象
     */
    public static <T> CamelResponse<T> success(T data) {
        return new CamelResponse<>(data);
    }

    /**
     * 返回成功响应.
     *
     * @param dataName 数据对象名称
     * @param data     数据对象
     * @return 响应对象
     */
    public static CamelResponse<Map<String, Object>> success(String dataName, Object data) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(dataName,data);
        return new CamelResponse<>(map);
    }

    /**
     * 返回有分页的成功响应.
     *
     * @param pageInfo 数据对象
     * @return 响应对象
     */
    public static <T> PageResponse successPage(PageInfo<T> pageInfo) {
        return new CamelPageResponse<>(pageInfo);
    }

    /**
     * 返回无数据的分页响应对象
     *
     * @return 无数据的分页响应对象
     */
    public static <T> PageResponse successEmptyPage() {
        PageInfo<T> emptyPageInfo = new PageInfo<>();
        return new CamelPageResponse<>(emptyPageInfo);
    }

    /**
     * 返回错误的响应.
     *
     * @param errorCode 错误编码
     * @param errorMsg  错误信息
     * @return 响应对象
     */
    public static Response error(Integer errorCode, String errorMsg) {
        return new CamelResponse(errorCode, errorMsg);
    }
}