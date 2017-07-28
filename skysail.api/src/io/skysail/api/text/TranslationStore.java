package io.skysail.api.text;

import java.util.Locale;
import java.util.Optional;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.BundleContext;

/**
 * A translation store implements a way to retrieve (and persist or update) translations
 * based on keys.
 *
 */
@ProviderType
public interface TranslationStore {

    Optional<String> get(String key);

    Optional<String> get(String key, ClassLoader cl);

    boolean persist(String key, String message, Locale locale, BundleContext bundleContext);

}
