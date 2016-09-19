package cn.dunn.constant;

/**
 * 来源
 */
public enum Source {
    PC("pc"), WEB("web"), ANDROID("android"), IOS("ios");
    private String source;

    Source(String source) {
        this.source = source;
    }

    public static Source valOf(String source) {
        switch (source) {
            case "pc":
                return PC;
            case "web":
                return WEB;
            case "android":
                return ANDROID;
            case "ios":
                return IOS;
            default:
                return null;
        }
    }
}
