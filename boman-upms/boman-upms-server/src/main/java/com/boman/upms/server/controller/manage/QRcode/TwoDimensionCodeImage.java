/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TwoDimensionCodeImage
 * Author:   Administrator
 * Date:     2019/4/17 11:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.upms.server.controller.manage.QRcode;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author jys
 * @create 2019/4/17
 * @since 1.0.0
 */

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

public class TwoDimensionCodeImage implements QRCodeImage {

    BufferedImage bufImg;

    public TwoDimensionCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

}
