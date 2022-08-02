package mybatis.dao;

import mybatis.beans.DmClientJdFinclip;

public interface DmClientJdFinclipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DmClientJdFinclip record);

    int insertSelective(DmClientJdFinclip record);

    DmClientJdFinclip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DmClientJdFinclip record);

    int updateByPrimaryKey(DmClientJdFinclip record);
}