package org.moxieapps.gwt.highcharts.client;

/**
 * A configurable class that will allow you to control the options for exporting module.  An instance of
 * this class can be constructed and then  set on the chart via the
 * {@link BaseChart#setExporting(Exporting)} method.
 * <p/>
 * Note that the "exporting" module must be included in the page in order for the exporting
 * navigation options to apply.  E.g.:
 * <p/>
 * &lt;script type="text/javascript" src="js/modules/exporting.js"&gt;&lt;/script&gt;
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class Exporting extends Configurable<Exporting> {

    /**
     * An enumeration of supported exporting file types, which can be passed to the
     * {@link Exporting#setType(Exporting.Type)} method.
     */
    public enum Type {

        /**
         * Portable Network Graphics file type
         */
        PNG("image/png"),

        /**
         * Joint Photographic Experts Group file type
         */
        JPEG("image/jpeg"),

        /**
         * Portable Document Format file type
         */
        PDF("application/pdf"),

        /**
         * Scalable Vector Graphics file type
         */
        SVG("image/svg+xml");

        private Type(String optionValue) {
            this.optionValue = optionValue;
        }

        private final String optionValue;

        public String toString() {
            return optionValue;
        }

    }

    // TODO: Add buttons configuration methods

    /**
     * Convenience method for setting the 'enabled' option for the exporting module.  Equivalent to:
     * <pre><code>
     *     exporting.setOption("enabled", true);
     * </code></pre>
     * Whether to enable the exporting module. Defaults to true, but note that the exporting module
     * Javascript file must be included in the source of the page in order for the exporting
     * functionality to appear.
     *
     * @param enabled Whether or not to enable or disable the exporting module for the chart.
     * @return A reference to this {@link Exporting} instance for convenient method chaining.
     */
    public Exporting setEnabled(boolean enabled) {
        return this.setOption("enabled", enabled);
    }

    /**
     * Convenience method for setting the 'filename' option for the exporting module.  Equivalent to:
     * <pre><code>
     *     exporting.setOption("filename", true);
     * </code></pre>
     * The filename, without extension, to use for the exported chart. Defaults to "chart".
     *
     * @param fileName The filename, without extension, to use for the exported chart.
     * @return A reference to this {@link Exporting} instance for convenient method chaining.
     */
    public Exporting setFilename(String fileName) {
        return this.setOption("fileName", fileName);
    }

    /**
     * Convenience method for setting the 'type' option for the exporting module.  Equivalent to:
     * <pre><code>
     *     exporting.setOption("type", "image/jpeg");
     * </code></pre>
     * Default MIME type for exporting if chart.exportChart() is called without specifying a type option.
     * Possible values are image/png, image/jpeg, application/pdf and image/svg+xml. Defaults to "image/png".
     *
     * @param type The default file format that exported charts should be created as.
     * @return A reference to this {@link Exporting} instance for convenient method chaining.
     */
    public Exporting setType(Type type) {
        return this.setOption("type", type != null ? type.toString() : null);
    }

    /**
     * Convenience method for setting the 'url' option for the exporting module.  Equivalent to:
     * <pre><code>
     *     exporting.setOption("url", "http://export.highcharts.com");
     * </code></pre>
     * The URL for the server module converting the SVG string to an image format. By default this
     * points to Highslide Software's free web service. Defaults to http://export.highcharts.com.
     *
     * @param url The URL for the server module converting the SVG string to an image format.
     * @return A reference to this {@link Exporting} instance for convenient method chaining.
     */
    public Exporting setUrl(String url) {
        return this.setOption("url", url);
    }

    /**
     * Convenience method for setting the 'width' option for the exporting module.  Equivalent to:
     * <pre><code>
     *     exporting.setOption("width", 600);
     * </code></pre>
     * The pixel width of charts exported to PNG or JPG. Defaults to 800.
     *
     * @param width The pixel width of charts exported to PNG or JPG.
     * @return A reference to this {@link Exporting} instance for convenient method chaining.
     */
    public Exporting setWidth(Number width) {
        return this.setOption("width", width);
    }

}
