package com.github.wnameless.spring.boot.up.data.mongodb.interceptor;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

/**
 * 
 * {@link SourceAndDocument} is designed to store both the source object and the MongoDB BSON
 * {@link Document} from certain {@link MongoMappingEvent}.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class SourceAndDocument {

  private final Object source;
  private final Document document;

  /**
   * Creates a {@link SourceAndDocument}.
   * 
   * @param source of a {@link MongoMappingEvent}
   * @param document of a {@link MongoMappingEvent}
   */
  public SourceAndDocument(Object source, Document document) {
    this.source = source;
    this.document = document;
  }

  /**
   * Returns the event source object.
   * 
   * @return a event source object
   */
  public Object getSource() {
    return source;
  }

  /**
   * Returns the event {@link Document}.
   * 
   * @return a event {@link Document}
   */
  public Document getDocument() {
    return document;
  }

  /**
   * Checks if the given type is matched with the event source type.
   * 
   * @param type to match with the event source type
   * @return true if given type and event source type are matched, otherwise false
   */
  public boolean hasSource(Class<?> type) {
    return type.isAssignableFrom(source.getClass());
  }

  /**
   * Returns a typed event source object by given type.
   * 
   * @param <T> type of returning object
   * @param type used to cast the event source
   * @return a typed event source object
   */
  @SuppressWarnings("unchecked")
  public <T> T getSource(Class<T> type) {
    return (T) source;
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, document);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SourceAndDocument other)) return false;
    return Objects.equals(source, other.source) && Objects.equals(document, other.document);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("source", source).append("document", document)
        .toString();
  }

}
