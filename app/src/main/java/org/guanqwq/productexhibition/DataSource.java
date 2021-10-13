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
                            new Product("", 0, "", 0)
                    },
                    R.drawable.huawei),
            new Type("Microsoft/微软",
                    new Product[] {

                    },
                    R.drawable.microsoft)
    };
}
