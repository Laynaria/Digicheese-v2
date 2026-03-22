import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { createUserSchema } from '@/schemas/user.schema'
import type { CreateUserInput } from '@/schemas/user.schema'
import { useCreateUser } from '@/hooks/useUsers'
import { useUIStore } from '@/store/ui.store'

export function UserForm() {
    const { mutate: createUser, isPending } = useCreateUser()
    const closeModal = useUIStore((s) => s.closeCreateUserModal)

    const { register, handleSubmit, formState: { errors }, reset } = useForm<CreateUserInput>({
        resolver: zodResolver(createUserSchema),
    })

    const onSubmit = (data: CreateUserInput) => {
        createUser(data, {
            onSuccess: () => {
                reset()
                closeModal()
            },
        })
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div>
                <input {...register('firstname')} placeholder="Prénom" />
                {errors.firstname && <span>{errors.firstname.message}</span>}
            </div>
            <div>
                <input {...register('lastname')} placeholder="Nom" />
                {errors.lastname && <span>{errors.lastname.message}</span>}
            </div>
            <div>
                <input {...register('email')} placeholder="Email" type="email" />
                {errors.email && <span>{errors.email.message}</span>}
            </div>
            <div>
                <input {...register('password')} placeholder="Mot de passe" type="password" />
                {errors.password && <span>{errors.password.message}</span>}
            </div>
            <button type="submit" disabled={isPending}>
                {isPending ? 'Création...' : 'Créer'}
            </button>
        </form>
    )
}