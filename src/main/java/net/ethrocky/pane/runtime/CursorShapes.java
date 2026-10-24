package net.ethrocky.pane.runtime;

//? if <26.3 {
/*import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;
*///?} else {
import com.mojang.blaze3d.platform.cursor.CursorType;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
//?}

/**
 * Hardware cursor shapes, selected from the render/main thread.
 * Shape 0 restores the default arrow.
 *
 * Non-default shapes are reasserted every frame while wanted.
 * The arrow is restored only on the transition back to shape 0.
 */
final class CursorShapes {
    //? if <26.3 {
    /*private static final Map<Integer, Long> CURSORS = new HashMap<>();
     *///?}

    private static int currentShape;

    private CursorShapes() {
    }

    static void set(long windowHandle, int shape) {
        if (shape != 0) {
            //? if <26.3 {
            /*long cursor = resolve(shape);
            if (cursor != 0L) {
                GLFW.glfwSetCursor(windowHandle, cursor);
            }
            *///?} else {
            resolve(shape).select();
            //?}

            currentShape = shape;
        } else if (currentShape != 0) {
            //? if <26.3 {
            /*GLFW.glfwSetCursor(windowHandle, 0L);
             *///?} else {
            CursorTypes.ARROW.select();
            //?}

            currentShape = 0;
        }
    }

    //? if <26.3 {
    /*private static long resolve(int shape) {
        long cursor = CURSORS.computeIfAbsent(
                shape, GLFW::glfwCreateStandardCursor
        );

        if (cursor == 0L) {
            cursor = CURSORS.computeIfAbsent(
                    GLFW.GLFW_RESIZE_EW_CURSOR,
                    GLFW::glfwCreateStandardCursor
            );
        }

        return cursor;
    }
    *///?} else {
    private static CursorType resolve(int shape) {
        // Preserve the existing GLFW shape IDs as this helper's input format.
        // These are IDs, not native cursor handles.
        return switch (shape) {
            case 0x00036001 -> CursorTypes.ARROW;
            case 0x00036002 -> CursorTypes.IBEAM;
            case 0x00036003 -> CursorTypes.CROSSHAIR;
            case 0x00036004 -> CursorTypes.POINTING_HAND;
            case 0x00036005 -> CursorTypes.RESIZE_EW;
            case 0x00036006 -> CursorTypes.RESIZE_NS;
            case 0x00036009 -> CursorTypes.RESIZE_ALL;
            case 0x0003600A -> CursorTypes.NOT_ALLOWED;

            // Includes diagonal resize IDs: use your existing EW fallback.
            default -> CursorTypes.RESIZE_EW;
        };
    }
    //?}
}
