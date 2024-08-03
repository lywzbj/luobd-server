package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.luobd.server.common.model.ResponseData;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {


   ResponseData<Boolean> delete(Long id);



  ResponseData<Boolean> batchDelete(List<Long> ids);












}
</#if>
