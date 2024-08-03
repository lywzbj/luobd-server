package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.luobd.server.common.model.ResponseData;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


  @Resource
  private ${table.mapperName} baseMapper;


@Override
@Transactional(rollbackFor = Exception.class)
public ResponseData<Boolean> delete(Long id) {
    final boolean remove = this.removeById(id);
    if(!remove) {
    return ResponseData.error("删除失败");
    }
    return ResponseData.success(Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Boolean> batchDelete(List<Long> ids) {
            final boolean remove = this.removeByIds(ids);
            if(!remove) {
            return ResponseData.error("删除失败");
            }
            return ResponseData.success(Boolean.TRUE);
            }






}
</#if>
