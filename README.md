# springnature

### configuração mysql
```yaml
# MySQL Database Connection Properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springnature_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```


### exemplo restcontrolleradvice
```java
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PosterNotFoundException.class)
    public ResponseEntity<ApiError> posterNotFoundException(PosterNotFoundException ex){
        ApiError error = ApiError
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .messages(List.of(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // DataIntegrityViolationException
    @ExceptionHandler(PosterTitleUniqueException.class)
    public ResponseEntity<ApiError> posterTitleUniqueException(PosterTitleUniqueException ex){
        ApiError error = ApiError
                .builder()
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .timestamp(LocalDateTime.now())
                .messages(List.of(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException ex, BindingResult result){

        ApiError error = ApiError
                .builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .timestamp(LocalDateTime.now())
                .errors(addErrors(result))
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(error);
    }

    private Map<String, String> addErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for(FieldError error : result.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

}

```

### exemplo paginação

```java
    @GetMapping("/search")
    public ResponseEntity<List<Poster>> search(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, PosterQueryFilter query){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posterService.findAll(pageable, query));
    }
```