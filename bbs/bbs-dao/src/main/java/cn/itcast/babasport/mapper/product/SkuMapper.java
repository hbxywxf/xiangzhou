package cn.itcast.babasport.mapper.product;

import cn.itcast.babasport.pojo.product.Sku;
import cn.itcast.babasport.pojo.product.SkuQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
    int countByExample(SkuQuery example);

    int deleteByExample(SkuQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Sku record);

    int insertSelective(Sku record);

    List<Sku> selectByExample(SkuQuery example);

    Sku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuQuery example);

    int updateByExample(@Param("record") Sku record, @Param("example") SkuQuery example);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);
}