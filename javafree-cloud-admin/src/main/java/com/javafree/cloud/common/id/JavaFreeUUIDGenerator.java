package com.javafree.cloud.common.id;

import com.javafree.cloud.common.utils.NanoIdUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;

/**
 * @version V1.0
 * @Description: 自定义jpa uuid生成工具类
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/9 11:41
 */

public class JavaFreeUUIDGenerator extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
       // String id=NanoIdUtils.randomNanoId();
        return NanoIdUtils.randomNanoId();
    }

}
