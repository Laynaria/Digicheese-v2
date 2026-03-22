import { createRoute } from '@tanstack/react-router'
import { Route as rootRoute } from './__root'
import { getUsersQueryOptions, useUsers } from '@/hooks/useUsers'
import { useUIStore } from '@/store/ui.store'
import { UserForm } from '@/components/UserForm'

export const Route = createRoute({
    getParentRoute: () => rootRoute,
    path: '/users',
    loader: ({ context: { queryClient } }) =>
        queryClient.ensureQueryData(getUsersQueryOptions()),
    component: UsersPage,
})

function UsersPage() {
    const { data: users, isLoading, isError } = useUsers()
    const { isCreateUserModalOpen, openCreateUserModal, closeCreateUserModal } = useUIStore()

    if (isLoading) return <p>Chargement...</p>
    if (isError) return <p>Erreur lors du chargement.</p>

    return (
        <div>
            <h1>Utilisateurs</h1>
            <button onClick={openCreateUserModal}>+ Créer un utilisateur</button>
            <ul>
                {users?.map((user) => (
                    <li key={user.id}>
                        {user.firstname} {user.lastname} — {user.email}
                    </li>
                ))}
            </ul>
            {isCreateUserModalOpen && (
                <div role="dialog">
                    <button onClick={closeCreateUserModal}>✕ Fermer</button>
                    <UserForm />
                </div>
            )}
        </div>
    )
}