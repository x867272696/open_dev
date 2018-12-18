package com.htl.cloudmemory.dao;

import com.htl.cloudmemory.entity.GItems;
import java.util.List;

public interface GItemsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Tue Dec 11 17:21:27 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Tue Dec 11 17:21:27 CST 2018
     */
    int insert(GItems record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Tue Dec 11 17:21:27 CST 2018
     */
    GItems selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Tue Dec 11 17:21:27 CST 2018
     */
    List<GItems> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbg.generated Tue Dec 11 17:21:27 CST 2018
     */
    int updateByPrimaryKey(GItems record);
}