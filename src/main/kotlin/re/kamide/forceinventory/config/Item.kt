package re.kamide.forceinventory.config

import org.jetbrains.annotations.Nullable

interface Item {
  val item: String
  @get:Nullable
  val name: String?
}

