package cn.com.goodlan.webvpn.utils;


import cn.com.goodlan.webvpn.pojo.entity.menu.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限数据处理
 *
 * @author ruoyi
 */
public abstract class TreeUtils {

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @return String
     */
    public static List<Menu> getChildPerms(List<Menu> list) {
        List<Menu> returnList = new ArrayList<>();
        for (Menu menu : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (menu.getParent() == null) {
                recursionFn(list, menu);
                returnList.add(menu);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static void recursionFn(List<Menu> list, Menu t) {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(null);
        t.setChildren(childList);
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<Menu> getChildList(List<Menu> list, Menu t) {
        List<Menu> returnList = new ArrayList<>();
        for (Menu n : list) {
            if (n.getParent() == null) {
                continue;
            }
            if (t.getId().equals(n.getParent().getId())) {
                returnList.add(n);
            }
        }
        return returnList;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list   分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
//    public List<Menu> getChildPerms(List<Menu> list, int typeId, String prefix) {
//        if (list == null) {
//            return null;
//        }
//        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext(); ) {
//            Menu node = iterator.next();
//            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
//            if (node.getParentId() == typeId) {
//                recursionFn(list, node, prefix);
//            }
//            // 二、遍历所有的父节点下的所有子节点
//            /*
//             * if (node.getParentId()==0) { recursionFn(list, node); }
//             */
//        }
//        return returnList;
//    }

//    private void recursionFn(List<Menu> list, Menu node, String p) {
//        // 得到子节点列表
//        List<Menu> childList = getChildList(list, node);
//        if (hasChild(list, node)) {
//            // 判断是否有子节点
//            returnList.add(node);
//            Iterator<Menu> it = childList.iterator();
//            while (it.hasNext()) {
//                Menu n = it.next();
//                n.setMenuName(p + n.getMenuName());
//                recursionFn(list, n, p + p);
//            }
//        } else {
//            returnList.add(node);
//        }
//    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<Menu> list, Menu t) {
        return getChildList(list, t).size() > 0;
    }
}
