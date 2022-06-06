import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@MappedSuperclass
@Access(AccessType.FIELD)
abstract class BaseEntity(


    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    open val id: Int?
) {
    @JsonIgnore
    fun isNew(): Boolean {
        return id == null
    }
}