package org.guanqwq.productexhibition;

import org.guanqwq.productexhibition.model.Product;
import org.guanqwq.productexhibition.model.Type;

public class DataSource {
    public static Type[] types = {
            new Type("Apple/苹果",
                    new Product[] {
                            new Product("iPhone 13", 5999,
                                    "超先进双摄系统，精进再精进。",
                                    R.drawable.iphone_13),
                            new Product("iPad mini", 3799,
                                    "WLAN版/A15芯片/全面屏/触控ID MK7M3CH/A",
                                    R.drawable.ipad_mini),
                            new Product("MacBook Pro", 9499,
                                    "八核M1芯片 8G 256G SSD 深空灰 笔记本电脑 轻薄本 MYD82CH/A",
                                    R.drawable.macbookpro)

                    },
                    R.drawable.apple),
            new Type("HUAWEI/华为",
                    new Product[] {
                            new Product("HUAWEI Mate 40 Pro", 6569,
                                    "【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+256GB亮黑色手机",
                                    R.drawable.huawei_mate40),
                            new Product("HUAWEI MatePad 11", 2799,
                                    "华为HUAWEI MatePad 11 2021款120Hz高刷全面屏 鸿蒙HarmonyOS 影音娱乐办公学习平板电脑6+128GB WIFI曜石灰",
                                    R.drawable.huawei_matepad11),
                            new Product("MateBook 13s 2021", 6999,
                                    "华为笔记本电脑MateBook 13s 2021 11代酷睿i5-11300H 16G 512G锐炬显卡/13.4英寸全面触控屏/轻薄办公本",
                                    R.drawable.huawei_matebook)
                    },
                    R.drawable.huawei),
            new Type("Microsoft/微软",
                    new Product[] {
                            new Product("Surface Pro 7", 14888,
                                    "微软Surface Pro 7酷睿i7+16G+512G亮铂金 二合一平板电脑 12.3英寸2.7K高色域触屏 高端轻薄本 笔记本电脑",
                                    R.drawable.surface_pro),
                            new Product("Surface Laptop Go", 5488,
                                    "微软Surface Laptop Go i5 8G+128G 冰晶蓝 笔记本电脑 12.4英寸3:2高色域全面屏触屏 金属商务办公轻薄本",
                                    R.drawable.laptop_go),
                            new Product("Xbox Series S/X", 468,
                                    "【新品】微软Xbox Series S/X无线控制器2020款电脑PC蓝牙steam手柄 xbox 无线控制器【磨砂黑+原装PC连接线】",
                                    R.drawable.xbox_series)
                    },
                    R.drawable.microsoft)
    };
}
